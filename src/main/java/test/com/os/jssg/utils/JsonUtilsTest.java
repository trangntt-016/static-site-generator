package test.com.os.jssg.utils;


import com.os.jssg.JssgApplication;
import com.os.jssg.utils.JSonUtils;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import test.com.os.jssg.TestUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonUtilsTest {
    private JSonUtils jsonUtils= new JSonUtils();

    @Test
    public void shouldReturnFromValidPath() throws IOException {
        // GIVEN
        String path = TestUtils.generateConfigPath();

        // WHEN
        JssgApplication result = jsonUtils.parseJSon(path);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    public void shouldThrowExceptionFromInValidPath() {
        // GIVEN
        String path = "nothing";

        // WHEN and THEN
        assertThatThrownBy(()->jsonUtils.parseJSon(path)).isInstanceOf(FileNotFoundException.class);
    }


}
