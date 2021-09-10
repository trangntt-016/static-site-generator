package com.os.jssg.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HTMLUtils {
    public static Map<String, String> convertTextToHTML(String pathStr){
        Map htmlMap = new HashMap();
        String html = "", title = "";
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(pathStr))) {
            String line;
            int breakLine = 0;

            while ((line = br.readLine()) != null) {
                // get title and set h1 tag
                if (breakLine == 0) {
                    title = line.toUpperCase(Locale.ROOT);
                    sb.append("<h1>"+line+"</h1>");
                }
                // append breaklines
                else if (line.equals("")) {
                    sb.append("<br/>");
                }
                // append <p> for normal lines
                else if(!line.equals("")) {
                    sb.append("<p>").append(line).append("</p>");
                }
                breakLine++;
            }
            // Complete HTML file
            html = "<!doctype html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"utf-8\">\n" +
                    "  <title>" + title + "</title>\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\"\n" +
                    "        integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">"+
                    "</head>\n" +
                    "<body class=\"container d-flex flex-column justify-content-center\">" + sb + "</body>\n" +
                    "</html>";

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Map.of("title",title,"body",html);
    }
}
