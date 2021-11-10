package test.com.os.jssg.utils;

import com.os.jssg.utils.HTMLUtils;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;
import test.com.os.jssg.TestUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


public class HTMLUtilsTest {
    private HTMLUtils utils = new HTMLUtils();

    @Test
    public void shouldReturnMapFromValidPathAndValidLanguage() throws IOException {
        // GIVEN
        String path = TestUtils.generateRandomPath();
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
        String path = TestUtils.generateRandomPath();
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
