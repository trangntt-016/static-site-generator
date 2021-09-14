package com.os.jssg.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HTMLUtils {
    public static Map<String, String> convertTextToHTML(String pathStr){
        Map htmlMap = new HashMap();
        StringBuilder sb = new StringBuilder();

        // read text and convert break line to <br/>
        String convertedText =TextUtils.readText(pathStr);

        String title = TextUtils.getTitleFromText(convertedText);

        String body = TextUtils.getBodyFromText(convertedText);

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

    public static String convertFileNamesToHTMLMenu(List<String> fileNames){
        StringBuilder sb = new StringBuilder();

        for(String f: fileNames){
            sb.append("<a href=\"/static-site-generator/"+f+".html"+"\"><h4 style=\"color: blue; text-align: center;font-weight:400\">"+f+"</h4></a>");
        }
        return sb.toString();
    }


}
