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

            JsonObject actual = objectMapper.readValue(reader, JsonObject.class);

            Assertions.assertEquals("John", actual.getName());
            Assertions.assertEquals(30, actual.getAge());
            Assertions.assertEquals("Moscow", actual.getAddress().getCity());
            Assertions.assertEquals("Academician Petrovsky Street", actual.getAddress().getStreet());

        }
    }
}
