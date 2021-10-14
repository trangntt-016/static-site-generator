package com.os.jssg.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MDUtils {
    private final Logger logger = LoggerFactory.getLogger(HTMLUtils.class);

    public Map<String, String> convertMdToHTML(String pathStr){
        Map htmlMap = new HashMap();
        StringBuilder sb = new StringBuilder();

        // read text and convert break line to <br/>
        String convertedText = new TextUtils().readText(pathStr);

        String title = MDUtils.getTitleFromReadMe(convertedText).replace("# ","");

        String body = MDUtils.getBodyFromReadMe(convertedText);

        // Complete HTML file
        String html = "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <title>" + title + "</title>\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\"\n" +
                "        integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">"+
                "</head>\n" +
                "<body class=\"container d-flex flex-column justify-content-center\">" + body + "</body>\n" +
                "</html>";

        return Map.of("title",title,"body",html);
    }

    public static String getTitleFromReadMe(String convertedText){
        String[] pDivs = convertedText.split("<br/>");

        return new TextUtils().toCapitalize(pDivs[0]).trim();
    }

    public static String getBodyFromReadMe(String convertedText){
        String[] lines = convertedText.split("<br/>");

        StringBuilder sb = new StringBuilder();


        for(int i = 0; i < lines.length; i++){
            if(lines[i].length()> 0 && lines[i].substring(0,2).equals("# ")){
                sb.append("<h1>").append(lines[i].replace("#","")).append("</h1>").append("<br/>");
            }
            else if(lines[i].length()==0){
                sb.append("<br/>");
            }
            // Add support for inline <code> blocks.
            else if(lines[i].contains("`")){
                // Parse the code blocks after a single backtick
                List<String> codeTokens = Arrays.stream(lines[i].split("`")).map(c-> c.trim()).collect(Collectors.toList());

                String afterCode = (codeTokens.size() > 4 && !codeTokens.get(4).isEmpty())?("<p>"+codeTokens.get(4)+"</p>"):"";

                // get rendered as <code>...text...</code>
                sb.append("<code>").append(codeTokens.get(1)).append("</code>").append(afterCode).append("</br>");
            }
            else{
                sb.append("<p>").append(lines[i]).append("</p>").append("<br/>");
            }
        }
        return sb.toString();
    }

}
