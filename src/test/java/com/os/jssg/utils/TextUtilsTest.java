package com.os.jssg.utils;

import org.junit.jupiter.api.Test;
import com.os.jssg.TestUtils;

import java.io.IOException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

public class TextUtilsTest {
    private TextUtil textUtils = new TextUtil();

    @Test
    void shouldReturnConvertedTextFromAValidPath() throws IOException {
        // GIVEN
        String path = TestUtils.generateRandomInputPath();

        // WHEN
        String convertedText = textUtils.readText(path);

        // THEN
        assertThat(convertedText).isNotEmpty();
    }

    @Test
    void shouldThrowNullPointerExceptionFromNullPath() {
        assertThatThrownBy(()->textUtils.readText(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldReturnEmptyFromInvalidPath() {
        // GIVEN
        String path = "invalid path";

        // WHEN
        String convertedText = textUtils.readText(path);

        // THEN
        assertThat(convertedText).isEmpty();
    }

    @Test
    void shouldReturnCapitalizeSentence() {
        // GIVEN
        String test = "new testing";

        // WHEN
        String capitalizedText = textUtils.toCapitalize(test);

        // THEN
        assertThat(capitalizedText).isEqualTo("New Testing");
    }

    @Test
    void shouldReturnBodyHTMLFromText() {
        // GIVEN
        String test = "<h1>Title</h1></br><p>New test</p";

        // WHEN
        String convertedText = textUtils.getBodyFromText(test);

        // THEN
        assertThat(convertedText).isEqualTo("<h1 style=\"text-align:center\"><h1>Title</h1></br><p>New test</p</h1><br/>");
    }

}

