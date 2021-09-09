package com.os.jssg.processor;

import com.os.jssg.utils.FileUtils;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


@NoArgsConstructor
public class HTMLProcessor {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public void convertToHTML(String pathStr, String outputPath) throws IOException {
        Path filePath = Paths.get(pathStr);

        if(!Files.exists(filePath)){
            logger.error("Cannot find any path with " + filePath);
            return;
        }

        // if input is a file
        if (pathStr.lastIndexOf(".txt") > 0) {
            try {
                Map htmlMap = FileUtils.convertTextToHTML(pathStr);

                // reset dist and create one if not exists
                FileUtils.resetDist();

                FileUtils.createHTMLFile(htmlMap, outputPath);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    e.printStackTrace();
                }
            });
        }

    }
}
