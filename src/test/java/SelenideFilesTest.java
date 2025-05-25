
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SelenideFilesTest {
    private final ClassLoader cl = SelenideFilesTest.class.getClassLoader();

    @Test
    @DisplayName("Проверка наличия файлов в архиве")
    public void zipFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("Archive.zip"))
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }
    }

    @Test
    @DisplayName("Проверка  pdf файла")
    public void pdfFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Archive.zip")) {
            assert is != null;
            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry zipEntry;
                while ((zipEntry = zis.getNextEntry()) != null) {
                    if (zipEntry.getName().endsWith(".pdf")) {
                        PDF pdf = new PDF(zis);
                        Assertions.assertEquals("Тестовый PDF-документ\n" +
                                "Здравствуйте!\n" +
                                "Это документ в формате PDF, который был создан для тестирования загрузки файлов.\n" +
                                "Никакой полезной информации он не несёт.",pdf.text);
                    }
                }
            }
        }
    }
    @Test
    @DisplayName("Проверка xls файла")
    public void xlsFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Archive.zip")) {
            assert is != null;
            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry zipEntry;
                while ((zipEntry = zis.getNextEntry()) != null) {
                    if (zipEntry.getName().endsWith(".pdf")) {
                        XLS xls = new XLS(zis);
                        Assertions.assertEquals("Тестовый PDF-документ\n" +
                                "Здравствуйте!\n" +
                                "Это документ в формате PDF, который был создан для тестирования загрузки файлов.\n" +
                                "Никакой полезной информации он не несёт.",pdf.text);
                    }
                }
            }
        }
    }
}
