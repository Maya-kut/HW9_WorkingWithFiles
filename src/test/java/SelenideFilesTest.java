
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SelenideFilesTest {
    private final ClassLoader cl = SelenideFilesTest.class.getClassLoader();

    @Test
    @DisplayName("Показ имен файлов в архиве")
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
    @DisplayName("Проверка pdf файла")
    public void pdfFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Archive.zip")) {
            assert is != null;
            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry zipEntry;
                while ((zipEntry = zis.getNextEntry()) != null) {
                    if (zipEntry.getName().endsWith(".pdf")) {
                        PDF pdf = new PDF(zis);
                        Assertions.assertEquals("Тестовый PDF-документ \n" +
                                " \n" +
                                "Здравствуйте! \n" +
                                "Это документ в формате PDF, который был создан для тестирования загрузки файлов. \n" +
                                "Никакой полезной информации он не несёт. \n", pdf.text);
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
                    if (zipEntry.getName().endsWith(".xls")) {
                        XLS xls = new XLS(zis);
                        String firstString = xls.excel
                                .getSheetAt(0)
                                .getRow(1)
                                .getCell(1)
                                .getStringCellValue();
                        String secondString = xls.excel
                                .getSheetAt(0)
                                .getRow(2)
                                .getCell(2)
                                .getStringCellValue();
                        Assertions.assertEquals("Внешний", firstString);
                        Assertions.assertEquals("Иванова", secondString);
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка csv файла")
    public void csvFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Archive.zip")) {
            assert is != null;
            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry zipEntry;
                while ((zipEntry = zis.getNextEntry()) != null) {
                    if (zipEntry.getName().endsWith(".csv")) {
                        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
                        CSVReader reader = new CSVReaderBuilder(new InputStreamReader(zis)).withCSVParser(csvParser).build();
                        List<String[]> data = reader.readAll();
                        Assertions.assertEquals(2, data.size());
                        Assertions.assertArrayEquals(new String[]{"CN001","OU001","iivanova@company.ru","88002000600","131","iivanova","iivanova"}, data.get(0));
                        Assertions.assertArrayEquals(new String[]{"CN002","OU002","ksergeev@company.ru","","201","ksergeev","ksergeev"}, data.get(1));
                    }
                }
            }
        }
    }
}
