package tests.ru.kev35.workWithFiles.zipArchiveAndJsonParsTests;


import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
public class FileParsingZipArchiveTests {

    private final ClassLoader cl = FileParsingZipArchiveTests.class.getClassLoader();

    @Test
    @DisplayName("Проверка, что файлы присутствуют в ZIP архиве")
    void checkFilesInZipArchiveTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("zipArchive.zip"))
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }
    }

    @Test
    @DisplayName("Проверка содержимого в CSV файле из ZIP архива")
    void checkContainCsvFileInZipArchiveTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("zipArchive.zip")))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if ("csvFile.csv".equals(entry.getName())) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));

                    List<String[]> data = csvReader.readAll();

                    Assertions.assertArrayEquals(new String[]{"All Items"}, data.get(0));
                    Assertions.assertArrayEquals(new String[]{"About"}, data.get(1));
                    Assertions.assertArrayEquals(new String[]{"Logout"}, data.get(2));
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка содержимого в PDF файле из ZIP архива")
    void checkContainPdfFileInZipArchiveTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("zipArchive.zip")))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if ("pdfFile.csv".equals(entry.getName())) {
                    PDF pdf = new PDF(zis);

                    Assertions.assertEquals("Тестовый PDF-документ",pdf.text);
                }
            }
        }
    }
    @Test
    @DisplayName("Проверка содержимого в XLSX файле из ZIP архива")
    void checkContainXlsxFileInZipArchiveTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("zipArchive.zip")))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if ("xlsx.csv".equals(entry.getName())) {
                    XLS xlsx = new XLS(zis);
                    String actualValue1 = xlsx.excel.getSheetAt(0).getRow(2).getCell(1).getStringCellValue();
                    String actualValue2 = xlsx.excel.getSheetAt(0).getRow(4).getCell(2).getStringCellValue();

                    Assertions.assertEquals("Привет мир",actualValue1);
                    Assertions.assertEquals("Привет мир",actualValue2);

                }
            }
        }
    }
    @AfterEach
    void closeWebDriver() {
        Selenide.closeWebDriver();
    }
}

