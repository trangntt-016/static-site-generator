package com.os.jssg.utils;

import static org.assertj.core.api.Assertions.*;

import com.os.jssg.TestUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;

public class HTMLUtilsTest {
  private HTMLUtils utils = new HTMLUtils();

  @Test
  public void shouldReturnMapFromValidPathAndValidLanguage() throws IOException {
    // GIVEN
    String path = TestUtils.generateRandomInputPath();
    String language = "en";

    // WHEN
    Map<String, String> result = utils.convertTextToHTML(path, language);

    // THEN
    assertThat(result).hasSize(2).containsKeys("title", "body");
  }

  @Test
  public void shouldReTurnNullFromInvalidPathAndValidLanguage() {
    // GIVEN
    String path = "invalid path";
    String language = "en";

    // WHEN
    Map<String, String> result = utils.convertTextToHTML(path, language);

    // THEN
    assertThat(result).isNull();
  }

  @Test
  public void shouldReTurnNullFromValidPathAndInValidLanguage() throws IOException {
    // GIVEN
    String path = TestUtils.generateRandomInputPath();
    String language = null;

    // WHEN
    Map<String, String> result = utils.convertTextToHTML(path, language);

    // THEN
    assertThat(result).isNull();
  }

  @Test
  public void shouldThrowsExceptionFromNullPathAndValidLanguage() {
    // GIVEN
    String path = null;
    String language = "en";

    // WHEN
    AbstractThrowableAssert throwable =
        assertThatThrownBy(() -> utils.convertTextToHTML(path, language));

    // THEN
    throwable.isInstanceOf(NullPointerException.class);
  }

  @Test
  public void shouldDeleteAllFilesInADirectory() throws IOException {
    // GIVEN
    String path = TestUtils.generateOutputPath();
    String inputPath = TestUtils.generateRandomInputPath();
    Map<String, String> map = new HTMLUtils().convertTextToHTML(inputPath, "en");
    new HTMLUtils().createHTMLFile(map, path);
    int beforeDelete = Files.list(Paths.get(path)).collect(Collectors.toList()).size();

    // WHEN
    new HTMLUtils().cleanDirectory(Paths.get(path));
    int afterDelete = Files.list(Paths.get(path)).collect(Collectors.toList()).size();

    // THEN
    assertThat(afterDelete).isEqualTo(0);
  }
}
