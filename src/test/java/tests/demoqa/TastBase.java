package tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TastBase {
    @BeforeAll
    static void setupEnvironment() {
        Configuration.browserSize = "1920x1080";
        //Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
    }
    @AfterEach
    void closeWebDriver() {
        Selenide.closeWebDriver();
    }

}