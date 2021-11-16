package com.os.jssg.utils;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;
import com.os.jssg.TestUtils;


import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


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
        Map<String, String>result = utils.convertTextToHTML(path, language);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    public void shouldReTurnNullFromValidPathAndInValidLanguage() throws IOException {
        // GIVEN
        String path = TestUtils.generateRandomInputPath();
        String language = null;

        // WHEN
        Map<String, String>result = utils.convertTextToHTML(path, language);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    public void shouldThrowsExceptionFromNullPathAndValidLanguage() {
        // GIVEN
        String path = null;
        String language = "en";

        // WHEN
        AbstractThrowableAssert throwable = assertThatThrownBy(()->utils.convertTextToHTML(path, language));

        // THEN
        throwable.isInstanceOf(NullPointerException.class);

    }
}
