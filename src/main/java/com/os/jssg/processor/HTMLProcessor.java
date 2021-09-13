package com.os.jssg.processor;

import com.os.jssg.utils.FileUtils;
import com.os.jssg.utils.HTMLUtils;
import com.os.jssg.utils.TextUtils;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
public class HTMLProcessor {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public void convertToHTML(String pathStr, String outputPath) {
        try{
            pathStr = TextUtils.processText(pathStr);

            Path filePath = Paths.get(pathStr);

            if(!Files.exists(filePath)){
                logger.error("Cannot find any path with " + filePath);
                return;
            }

            // if input is a file
            if (pathStr.lastIndexOf(".txt") > 0) {
                Map htmlMap = HTMLUtils.convertTextToHTML(pathStr);

                // reset dist and create one if not exists
                FileUtils.resetDist();

                FileUtils.createHTMLFile(htmlMap, outputPath);

            }
            // if input is a folder
            else {
                // reset dist and create one if not exists
                FileUtils.resetDist();
                List<String>HTMLFileNames = new ArrayList<>();

                Files.list(filePath).forEach(f->{
                    Map htmlMap = HTMLUtils.convertTextToHTML(f.toAbsolutePath().toString());
                    HTMLFileNames.add(htmlMap.get("title").toString());
                    try {
                        FileUtils.createHTMLFile(htmlMap, outputPath);

                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                });
                FileUtils.createIndexHTML(HTMLFileNames, outputPath);
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
