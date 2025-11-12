package tests.ru.kev35.workWithFiles;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class SelenideDownloadUploadFilesTests {

    @Test
    @Tag("Скачивание файла с веб сайта в форматe(ссылка) .md при помощи selenide")
    void downloadFileTest() throws Exception
    {
        open("https://github.com/junit-team/junit-framework/blob/main/README.md");
        File downloaded = $(".react-blob-header-edit-and-raw-actions [href*='/main/README.md']").download();
        //только если есть href в кнопке, если нет используем прокси Configuration.fileDownload = FileDownloadMode.PROXY

        try(InputStream is = new FileInputStream(downloaded)){
            byte[] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);

            Assertions.assertTrue(dataAsString.contains("This repository is the home of JUnit Platform, Jupiter, and Vintage."));
        }
    }

    @Test
    @Tag("Загрузка файла на веб сайт из папки resorces")
    void uploadFileTest() {

        open("https://guides.kontur.ru/components/input-fields/file-uploader/");
        Configuration.timeout = Long.parseLong("6000");
        $("input[type='file']").uploadFromClasspath("tex.txt");
        $("[data-tid='FileUploader__fileName']").shouldHave(text("tex.txt"));
    }

}
