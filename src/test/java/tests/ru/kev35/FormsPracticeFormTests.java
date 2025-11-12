package tests.ru.kev35;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class FormsPracticeFormTests {
    @BeforeAll
    static void setupEnvironment() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void successFillingFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("#firstName").setValue("Don");
        $("#lastName").setValue("Carlione");
        $("#userEmail").setValue("Carl@mail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9001112222");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2008");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("1.png");
        $("#currentAddress").setValue("Lesovskay 3");
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Noida").pressEnter();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Don Carlione"),
                text("Carl@mail.ru"),
                text("Male"),
                text("9001112222"),
                text("30 July,2008"),
                text("Maths"),
                text("Sports"),
                text("1.png"),
                text("Lesovskay 3"),
                text("NCR Noida"));
        $("#closeLargeModal").click();
    }

    @Test
    void MinimumDataToFillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("#firstName").setValue("Don");
        $("#lastName").setValue("Carlione");
        $("#genterWrapper").find(byText("Male")).click();
        $("#userNumber").setValue("9001112222");
        $("#submit").click();
        $(".table-responsive").shouldHave(text("Don Carlione"), text("Male"), text("9001112222"));
        $("#closeLargeModal").click();
    }

    @Test
    void FildLastNameIsNotInTest() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("#firstName").setValue("Don");
        $("#lastName").setValue("");
        $("#genterWrapper").find(byText("Male")).click();
        $("#userNumber").setValue("9001112222");
        $("#submit").click();
        $(".modal-content").shouldNot();
    }
}
