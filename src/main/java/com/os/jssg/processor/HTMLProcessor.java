package com.os.jssg.processor;

import com.os.jssg.utils.FileUtils;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


@NoArgsConstructor
public class HTMLProcessor {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public void convertToHTML(String pathStr, String outputPath) {
        try{
            pathStr = FileUtils.processText(pathStr);

            Path filePath = Paths.get(pathStr);

            if(!Files.exists(filePath)){
                logger.error("Cannot find any path with " + filePath);
                return;
            }

            // if input is a file
            if (pathStr.lastIndexOf(".txt") > 0) {
                Map htmlMap = FileUtils.convertTextToHTML(pathStr);

                // reset dist and create one if not exists
                FileUtils.resetDist();

                FileUtils.createHTMLFile(htmlMap, outputPath);

            }
            // if input is a folder
            else {
                // reset dist and create one if not exists
                FileUtils.resetDist();

                Files.list(filePath).forEach(f->{
                    Map htmlMap = FileUtils.convertTextToHTML(f.toAbsolutePath().toString());
                    try {
                        FileUtils.createHTMLFile(htmlMap, outputPath);
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                });
            }
        }
        catch(IOException ex){
            logger.error(ex.getMessage());
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }

    }
}
