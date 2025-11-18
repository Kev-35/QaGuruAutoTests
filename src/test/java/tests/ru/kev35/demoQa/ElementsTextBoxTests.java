package tests.ru.kev35.demoQa;
import data.TestData;
import org.junit.jupiter.api.Test;
import pages.ElementsTextBoxPage;

public class ElementsTextBoxTests extends TestBase {

    ElementsTextBoxPage registration = new ElementsTextBoxPage();

    @Test
    void fillFormTest() {
        registration.openPage()
                .fullName(TestData.firstName)
                .email(TestData.email)
                .currentAddress(TestData.currentAddress)
                .permanentAddress("Another street 5")
                .submitButton()

                .checkResult("Name:", TestData.firstName)
                .checkResult("Email:", TestData.email)
                .checkResult("Current Address :", TestData.currentAddress)
                .checkResult("Permananet Address :", "Another street 5");

    }
}