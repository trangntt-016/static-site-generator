package com.os.jssg.utils;

import com.github.rjeschke.txtmark.Processor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MDUtils {
  private final Logger logger = LoggerFactory.getLogger(HTMLUtils.class);

  public Map<String, String> convertMdToHTML(String pathStr) {

    // read text and convert break line to <br/>
    String convertedText = new TextUtil().readMdText(pathStr);

    if(convertedText.isEmpty()) return null;

    String title = new MDUtils().getTitleFromReadMe(convertedText);

    String body = Processor.process(convertedText);

    // Complete HTML file
    String html =
        "<!doctype html>\n"
            + "<html lang=\"en\">\n"
            + "<head>\n"
            + "  <meta charset=\"utf-8\">\n"
            + "  <title>"
            + title
            + "</title>\n"
            + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
            + "  <link rel=\"stylesheet\""
            + " href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\"\n"
            + "       "
            + " integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\""
            + " crossorigin=\"anonymous\"></head>\n"
            + "<body class=\"container d-flex flex-column justify-content-center\">"
            + body
            + "</body>\n"
            + "</html>";

    return Map.of("title", title, "body", html);
  }

  public String getTitleFromReadMe(String convertedText) {
    String[] pDivs = convertedText.split("</h1>");

    return new TextUtil().toCapitalize(pDivs[0]).trim().replace("<h1>", "");
  }

  public String getBodyFromReadMe(String convertedText) {
    String[] lines = convertedText.split("<br/>");

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < lines.length; i++) {
      if (lines[i].length() > 0 && lines[i].substring(0, 2).equals("# ")) {
        sb.append("<h1>").append(lines[i].replace("#", "")).append("</h1>").append("<br/>");
      } else if (lines[i].length() == 0) {
        sb.append("<br/>");
      }
      // Add support for inline <code> blocks.
      else if (lines[i].contains("`")) {
        // Parse the code blocks after a single backtick
        List<String> codeTokens =
            Arrays.stream(lines[i].split("`")).map(c -> c.trim()).collect(Collectors.toList());

        String afterCode =
            (codeTokens.size() > 4 && !codeTokens.get(4).isEmpty())
                ? ("<p>" + codeTokens.get(4) + "</p>")
                : "";

        // get rendered as <code>...text...</code>
        sb.append("<code>")
            .append(codeTokens.get(1))
            .append("</code>")
            .append(afterCode)
            .append("</br>");
      } else {
        sb.append("<p>").append(lines[i]).append("</p>").append("<br/>");
      }
    }
    return sb.toString();
  }
}

