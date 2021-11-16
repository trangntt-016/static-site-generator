package com.os.jssg.utils;

import org.junit.jupiter.api.Test;
import com.os.jssg.TestUtils;

import java.io.IOException;

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

}

