package com.os.jssg.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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

    public static void createIndexHTML(List<String>fileNames,  String outputPathStr)throws IOException{
        Path outputPath = Paths.get(outputPathStr);

        if(!Files.exists(outputPath)){
            logger.error("Specified output path is not a valid directory");
            return;
        }

        // write new html files to dist
        StringBuilder sb = new StringBuilder();
        String bodyContent = FileUtils.generateStoriesHTML(fileNames);
        sb.append("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <title>Generated Pages</title>\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\"\n" +
                "        integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\"></head>\n" +
                "<body class=\"container d-flex flex-column justify-content\" style=\"background: #161f27\">\n" +
                "<h1 style=\"color: white; text-align: center;margin-bottom:30px;\">Generated Pages</h1>");
        sb.append(bodyContent).append("</body></html>");

        Path path = Paths.get(outputPath.toString() +"\\"+ "index.html");


        byte[] strToBytes = sb.toString().getBytes();

        Files.write(path, strToBytes);
    }

    public static String generateStoriesHTML(List<String>fileNames){
        StringBuilder sb = new StringBuilder();

        for(String f: fileNames){
            sb.append("<a href=\"/"+f+".html"+"\"><h4 style=\"color: blue; text-align: center;font-weight:400\">"+f+"</h4></a>");
        }
        return sb.toString();
    }


}


