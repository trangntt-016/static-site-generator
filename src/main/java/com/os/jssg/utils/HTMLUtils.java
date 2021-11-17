package com.os.jssg.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTMLUtils {
  private final Logger logger = LoggerFactory.getLogger(HTMLUtils.class);

  public Map<String, String> convertTextToHTML(String pathStr, String language) {
    // read text and convert break line to <br/>
    String convertedText = new TextUtil().readText(pathStr);

    if (convertedText.isEmpty() || language == null) {
      return null;
    }

    String title = new TextUtil().getTitleFromText(convertedText);

    String body = new TextUtil().getBodyFromText(convertedText);

    // Complete HTML file
    String html =
        "<!doctype html>\n"
            + "<html lang="
            + language
            + ">\n"
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

  public String convertFileNamesToHTMLMenu(List<String> fileNames) {
    StringBuilder sb = new StringBuilder();

    for (String f : fileNames) {
      sb.append(
          "<a href=\"/static-site-generator/"
              + f
              + ".html"
              + "\"><h4 style=\"color: blue; text-align: center;font-weight:400\">"
              + f
              + "</h4></a>");
    }
    return sb.toString();
  }

  public void cleanDirectory(Path filePath) {
    try {
      Files.list(filePath)
          .forEach(
              f -> {
                try {
                  Files.delete(f);
                } catch (IOException e) {
                  logger.error("Cannot delete file " + f);
                }
              });
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void createHTMLFile(Map<String, String> htmlMap, String outputPathStr) {
    Path outputPath = Paths.get(outputPathStr);

    if (!Files.exists(outputPath)) {
      // logger.error("Specified output path is not a valid directory");
      return;
    }
    try {
      // write new html files to dist
      Path path = Paths.get(outputPath + "\\" + htmlMap.get("title") + ".html");
      Charset charset = Charset.forName("UTF-8");
      byte[] strToBytes = htmlMap.get("body").getBytes(charset);

      Files.write(path, strToBytes);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void resetDist() throws IOException {
    Path distPath = Paths.get("./dist");

    if (Files.exists(distPath)) {
      cleanDirectory(distPath);
    } else {
      Files.createDirectories(distPath);
    }
  }

  public void createIndexHTML(List<String> fileNames, String outputPathStr) throws IOException {
    StringBuilder sb = new StringBuilder();

    Path outputPath = Paths.get(outputPathStr);

    if (!Files.exists(outputPath)) {
      throw new IOException("Specified output path is not a valid directory");
    }

    // write new html files to dist
    String bodyContent = new HTMLUtils().convertFileNamesToHTMLMenu(fileNames);

    sb.append(
        "<!doctype html>\n"
            + "<html lang=\"en\">\n"
            + "<head>\n"
            + "  <meta charset=\"utf-8\">\n"
            + "  <title>Generated Pages</title>\n"
            + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
            + "  <link rel=\"stylesheet\""
            + " href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\"\n"
            + "       "
            + " integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\""
            + " crossorigin=\"anonymous\"></head>\n"
            + "<body class=\"container d-flex flex-column justify-content\" style=\"background:"
            + " #161f27\">\n"
            + "<h1 style=\"color: white; text-align: center;margin-bottom:30px;\">Generated"
            + " Pages</h1>");

    sb.append(bodyContent).append("</body></html>");

    Path path = Paths.get(outputPath + "\\" + "index.html");

    Charset charset = Charset.forName("UTF-8");
    byte[] strToBytes = sb.toString().getBytes(charset);

    Files.write(path, strToBytes);
  }
}
