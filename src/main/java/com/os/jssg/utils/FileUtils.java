package com.os.jssg.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


}


