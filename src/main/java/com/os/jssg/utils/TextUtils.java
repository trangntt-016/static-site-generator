package com.os.jssg.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class TextUtils {
    private final Logger logger = LoggerFactory.getLogger(TextUtils.class);

    public String processText(String text) {
        return text.replace("-", " ").replace("./", "").replace("'", "").replace("\"", "");
    }

    public String readText(String pathStr) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(pathStr))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    sb.append("<br/>");
                } else {
                    sb.append(line).append(" ");
                }
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String toCapitalize(String title){
        StringBuilder sb = new StringBuilder();
        String[] strings = title.split(" ");

        for(String s: strings){
            sb.append(s.substring(0,1).toUpperCase(Locale.ROOT)+s.substring(1).toLowerCase(Locale.ROOT)).append(" ");
        }

        return sb.toString();

    }

    public  String getTitleFromText(String convertedText){
        String[] pDivs = convertedText.split("<br/>");

        return new TextUtils().toCapitalize(pDivs[0]).trim();
    }

    public String getBodyFromText(String convertedText){
        String[] lines = convertedText.split("<br/>");


        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < lines.length; i++){
            if(i==0){
                sb.append("<h1 style=\"text-align:center\">").append(lines[i]).append("</h1>").append("<br/>");
            }
            else if(lines[i].length()==0){
                sb.append("<br/>");
            }
            else{
                sb.append("<p>").append(lines[i]).append("</p>").append("<br/>");
            }
        }
        return sb.toString();
    }
}
