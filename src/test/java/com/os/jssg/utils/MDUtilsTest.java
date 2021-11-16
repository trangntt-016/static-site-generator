package com.os.jssg.utils;


import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;
import com.os.jssg.TestUtils;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MDUtilsTest {
    private MDUtils utils = new MDUtils();

    @Test
    public void shouldReturnMapFromValidPath() throws IOException {
        // GIVEN
        String path = TestUtils.generateMDFile();

        // WHEN
        Map<String, String> result = utils.convertMdToHTML(path);

        // THEN
        assertThat(result).hasSize(2).containsKeys("title", "body");
    }

    @Test
    public void shouldReTurnNullFromInvalidPath(){
        // GIVEN
        String path = "invalid path";

        // WHEN
        Map<String, String>result = utils.convertMdToHTML(path);

        // THEN
        assertThat(result).isNull();
    }


    @Test
    public void shouldThrowsExceptionFromNullPath(){
        // GIVEN
        String path = null;

        // WHEN
        AbstractThrowableAssert throwable = assertThatThrownBy(()->utils.convertMdToHTML(path));

        // THEN
        throwable.isInstanceOf(NullPointerException.class);

    }
}
