package tests.ru.kev35.allureReportsTests.allureReportsHomeWork;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class StepsForAllureTests {

    @Step("Открываем страницу репозитория")
    public void openPageRepository(String repo){
        open(repo);
    }
    @Step("Клик по кнопке Issue")
    public  void clickOnButtonIssue(){
        $("#issues-tab").click();
    }
    @Step("Клик на задачу IssueForAllureTest")
    public void clickOnIssueForAllureTest(){
        $("[href='/Kev-35/QaGuruAutoTests/issues/1']").click();
    }
    @Step("Проверка ожидаемого результата. Текст в задаче - AllureTest")
    public void checkTextInIssue(String expectedResult){
        $("[dir=auto]").shouldHave(Condition.text(expectedResult));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
