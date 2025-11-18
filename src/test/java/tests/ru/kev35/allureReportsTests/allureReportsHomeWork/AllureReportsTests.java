package tests.ru.kev35.allureReportsTests.allureReportsHomeWork;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AllureReportsTests {

    public static String REPOSITORY = "Kev-35/QaGuruAutoTests";
    public static String EXPECTED_RESULT = "AllureTest";

    @BeforeAll
    static void setupEnvironment() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    @Owner("Kuznetcov E.V.")
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "IssueLink",url = "https://https://github.com/Kev-35/QaGuruAutoTests/issues/1")
    @DisplayName("Проверка присутствия Issue на GitHub (SelenideTest)")
    public void checkIssueSelenideTest(){

        open(REPOSITORY);
        $("#issues-tab").click();
        $("[href='/Kev-35/QaGuruAutoTests/issues/1']").click();
        $("[dir=auto]").shouldHave(Condition.text(EXPECTED_RESULT));
    }


    @Test
    @DisplayName("Проверка присутствия Issue на GitHub with steps (SelenideTest)")
    public void checkIssueSelenideWithStepsTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открытие страницы репозитория" , () -> {
            open(REPOSITORY);
        });
        step("Клик по кнопке Issue", () ->{
                    $("#issues-tab").click();
        });
        step("Клик на задачу IssueForAllureTest", () ->{
            $("[href='/Kev-35/QaGuruAutoTests/issues/1']").click();
        });
        step("Проверка ожидаемого результата. Текст в задаче - AllureTest", () ->{
            $("[dir=auto]").shouldHave(Condition.text(EXPECTED_RESULT));
        });
        attachment("Source", Objects.requireNonNull(webdriver().driver().source()));
    }

    @Test
    @DisplayName("Проверка присутствия Issue на GitHub with @Step (SelenideTest)")
    public void checkIssueSelenideWithPageStepsTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        StepsForAllureTests step = new StepsForAllureTests();

        step.openPageRepository(REPOSITORY);
        step.clickOnButtonIssue();
        step.clickOnIssueForAllureTest();
        step.checkTextInIssue(EXPECTED_RESULT);
        step.takeScreenshot();

    }
}
