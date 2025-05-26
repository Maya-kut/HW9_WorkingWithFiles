import Model.ModelPhone;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class JsonParsingTest {
    private final ClassLoader cl = SelenideFilesTest.class.getClassLoader();

    @Test
    @DisplayName("Проверка json файла")
    void jsonFileParsingImprovedTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(cl.getResourceAsStream("modelPhone.json"))
        )) {
            ObjectMapper objectMapper = new ObjectMapper();

            ModelPhone actual = objectMapper.readValue(reader, ModelPhone.class);

            Assertions.assertEquals("iPhone 13", actual.getModel());
            Assertions.assertEquals("Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.4 Mobile/15E148 Safari/604.1", actual.getUserAgent());
            Assertions.assertEquals(390, actual.getScreen().getWidth());
            Assertions.assertEquals(844, actual.getScreen().getHeight());
            Assertions.assertEquals(390, actual.getViewport().getWidth());
            Assertions.assertEquals(664, actual.getViewport().getHeight());
            Assertions.assertEquals(3, actual.getDeviceScaleFactor());
            Assertions.assertTrue(actual.isMobile());
            Assertions.assertTrue(actual.HasTouch());
            Assertions.assertEquals("webkit", actual.getDefaultBrowserType());


        }
    }
}
