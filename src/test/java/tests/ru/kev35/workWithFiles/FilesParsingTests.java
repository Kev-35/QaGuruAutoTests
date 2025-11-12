package tests.ru.kev35.workWithFiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FilesParsingTests {

    private final ClassLoader cl = FilesParsingTests.class.getClassLoader();
    private static final Gson gson = new Gson();

    @Test
    @DisplayName("Распарсиваем файлы в формате .PDF") //библиотека testImplementation("com.codeborne:pdf-test:1.5.0")
    void pdfFileParsingTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");

        File downloaded = $("[href*='junit-user-guide-6.0.1.pdf']").download();
        PDF pdf = new PDF(downloaded);

        Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein", pdf.author);
    }
    @Test
    @DisplayName("Парсинг файла в формате .XLS(exel) и .XLSX") //библиотека testImplementation("com.codeborne:xls-test:1.4.3")
    void xlsFileParsingTest() throws Exception {
        open("https://excelvba.ru/programmes/Teachers?ysclid=lfcu77j9j9951587711");

        File downloaded = $("[href='https://ExcelVBA.ru/sites/default/files/teachers.xls']").download();
        XLS xls = new XLS(downloaded);
        String actualValue = xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();

        Assertions.assertTrue(actualValue.contains("Суммарное количество часов планируемое на штатную по всем разделам"));
    }

    @Test
    @DisplayName("Парсинг файла в формате .CSV") // библиотека testImplementation("com.opencsv:opencsv:5.12.0")
    void csvFileParsingTest() throws Exception {

        try (InputStream is = cl.getResourceAsStream("parametrizedTestDataFile.csv.csv")) {
            Assertions.assertNotNull(is);
            try (CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {

                List<String[]> data = csvReader.readAll();

                Assertions.assertEquals(2, data.size());
                Assertions.assertArrayEquals(
                        new String[] {"Selenide", "https://selenide.org"},
                        data.get(0)
                );
                Assertions.assertArrayEquals(
                        new String[] {"JUnit 5","https://junit.org"},
                        data.get(1)
                );
            }
        }
    }

    @Test
    @DisplayName("Парсинг файла в формате .ZIP") //Посмотреть какие файлы содержаться в  Zip архиве
    void zipFileParsingTest() throws Exception{
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("zipArchive.zip"))))
        {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null ){
                System.out.println(entry.getName());
            }
        }
    }

    @Test
    @DisplayName("Парсинг файла в формате JSON") // При помощи библиотеки gson
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(cl.getResourceAsStream("glossary.json"))))

        {
            JsonObject actual = gson.fromJson(reader, JsonObject.class);

            Assertions.assertEquals("example glossary", actual.get("title").getAsString());
            Assertions.assertEquals(234234, actual.get("ID").getAsInt());

            JsonObject inner = actual.get("glossary").getAsJsonObject();

            Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
            Assertions.assertEquals("Standard Generalized Markup Language", inner.get("GlossTerm").getAsString());
        }
    }

    @AfterEach
    void closeWebDriver() {
        Selenide.closeWebDriver();
    }
}

