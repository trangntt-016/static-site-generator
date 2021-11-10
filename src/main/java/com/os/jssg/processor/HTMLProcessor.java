package com.os.jssg.processor;

import com.os.jssg.utils.HTMLUtils;
import com.os.jssg.utils.MDUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor
public class HTMLProcessor {
  private final Logger logger = LoggerFactory.getLogger(HTMLProcessor.class);

  public void convertToHTML(String pathStr, String outputPath, String language) {
    try {
      if (!Files.exists(Paths.get(pathStr))) {
        logger.error("Cannot find any path with " + pathStr);
        return;
      }

      // if input is a txt file
      if (pathStr.lastIndexOf(".txt") > 0) {
        Map htmlMap = new HTMLUtils().convertTextToHTML(pathStr, language);

        // reset dist and create one if not exists
        new HTMLUtils().resetDist();

        new HTMLUtils().createHTMLFile(htmlMap, outputPath);

      }
      // if input is a md file
      else if (pathStr.lastIndexOf(".md") > 0) {
        Map htmlMap = new MDUtils().convertMdToHTML(pathStr);

        // reset dist and create one if not exists
        new HTMLUtils().resetDist();

        new HTMLUtils().createHTMLFile(htmlMap, outputPath);
      }
      // if input is a folder
      else {
        // reset dist and create one if not exists
        new HTMLUtils().resetDist();
        List<String> HTMLFileNames = new ArrayList<>();

        Files.list(Paths.get(pathStr))
            .forEach(
                f -> {
                  Map htmlMap =
                      new HTMLUtils().convertTextToHTML(f.toAbsolutePath().toString(), language);
                  HTMLFileNames.add(htmlMap.get("title").toString());
                  try {
                    new HTMLUtils().createHTMLFile(htmlMap, outputPath);

                  } catch (IOException e) {
                    logger.error(e.getMessage());
                  }
                });
        new HTMLUtils().createIndexHTML(HTMLFileNames, outputPath);
      }
    } catch (IOException ex) {
      logger.error(ex.getMessage());
    } catch (Exception ex) {
      logger.error(ex.getMessage());
    }
  }
}
