package tests.demoqa;

import com.codeborne.selenide.Condition;
import data.ParametrizedEnumData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Аннотациии + Параметризованные тесты Junit")
public class ParametrizedTests extends TastBase {

    @ValueSource(strings = {
            "standard_user",
            "problem_user"
    })
    @ParameterizedTest(name = "Проверка валидного имени {0} для регистрации")
    public void successNameForRegistrationSwagLabs(String name) {

        open("https://www.saucedemo.com/");
        $("[data-test = username]").setValue(name);
        $("[data-test = password]").setValue("secret_sauce");
        $("[data-test = login-button]").pressEnter();
        $(".app_logo").shouldHave(Condition.text("Swag Labs"));
    }

    @CsvSource(value = {
            "standard_user,secret sauce",
            "visual_user, secret",
            "problem_user,_sauce"
    })
    @ParameterizedTest(name = "Проверка валидного имени {0} с невалидным паролем {1}")
    public void unsuccessPasswordForRegistrationSwagLabs(String name,String password ) {

        open("https://www.saucedemo.com/");
        $("[data-test = username]").setValue(name);
        $("[data-test = password]").setValue(password);
        $("[data-test = login-button]").pressEnter();
        $("[data-test = error]")
                .shouldHave(Condition.text("Epic sad face: Username and password " +
                        "do not match any user in this service"));
    }

    @CsvFileSource(resources = "/parametrizedTestDataFile")
    @ParameterizedTest(name = "Проверка наполнения гамбургера полем {0} ")
    public void fillingHamburgerMenuFields(String value ) {

        open("https://www.saucedemo.com/");
        $("[data-test = username]").setValue("standard_user");
        $("[data-test = password]").setValue("secret_sauce");
        $("[data-test = login-button]").pressEnter();
        $("#react-burger-menu-btn").click();
        $(".bm-item-list").shouldHave(Condition.text(value));
    }

    @EnumSource(ParametrizedEnumData.class)
    @ParameterizedTest(name = "Проверка {0} языка на странице Selenide")
    public void CheckLanguageForSelenide(ParametrizedEnumData lang) {

        open("https://selenide.org/");
        $$("#languages a").find(Condition.text(lang.name())).click();
        $(".wrapper-color-content h1").shouldHave(Condition.text(lang.description));
    }
}
