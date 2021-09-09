package com.os.jssg.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static void cleanDirectory(Path filePath) {
        try {
            Files.list(filePath).forEach(f -> {
                try {
                    Files.delete(f);
                } catch (IOException e) {
                    logger.error("Cannot delete file " + f);
                }

            });
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

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
                    title = line;
                    sb.append("<h1>"+line+"</h1>");
                }
                // append breaklines
                else if (line.equals("")) {
                    sb.append("<br/>");
                }
                // append <p> for normal lines
                else if(!line.equals("")) {
                    sb.append("<p>" + line + "</p>");
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
                    "</head>\n" +
                    "<body>" + sb + "</body>\n" +
                    "</html>";

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Map.of("title",title,"body",html);
    }

    public static void createHTMLFile(Map<String, String>htmlMap, String outputPathStr) throws IOException {
        Path outputPath = Paths.get(outputPathStr);

        if(!Files.exists(outputPath)){
            logger.error("Specified output path is not a valid directory");
            return;
        }

        // write new html files to dist
        Path path = Paths.get(outputPath.toString() +"\\"+ htmlMap.get("title") + ".html");

        byte[] strToBytes = htmlMap.get("body").toString().getBytes();

        Files.write(path, strToBytes);
    }

    public static void resetDist() throws IOException {
        Path distPath = Paths.get("./dist");
        if(Files.exists(distPath)){
            cleanDirectory(distPath);
        }
        else{
            Files.createDirectories(distPath);
        }
    }

    public static String processText(String text){
        return text.replace("-"," ").replace("./","").replace("'","").replace("\"","");
    }
}


