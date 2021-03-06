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

@NoArgsConstructor
public class HTMLProcessor {
  public void convertToHTML(String pathStr, String outputPath, String language) {
    try {
      if (!Files.exists(Paths.get(pathStr))) {
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
                  new HTMLUtils().createHTMLFile(htmlMap, outputPath);
                });
        new HTMLUtils().createIndexHTML(HTMLFileNames, outputPath);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
