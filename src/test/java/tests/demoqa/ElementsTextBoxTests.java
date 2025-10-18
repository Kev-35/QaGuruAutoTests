package tests.demoqa;
import org.junit.jupiter.api.Test;
import pages.ElementsTextBoxPage;
public class ElementsTextBoxTests extends TastBase {

    ElementsTextBoxPage registration = new ElementsTextBoxPage();

    @Test
    void fillFormTest() {
        registration.openPage()
                .fullName("Alex")
                .email("alex@egorov.com")
                .currentAddress("Some street 1")
                .permanentAddress("Another street 1")
                .submitButton()

                .checkResult("Name:","Alex")
                .checkResult("Email:","alex@egorov.com")
                .checkResult("Current Address :","Some street 1")
                .checkResult("Permananet Address :","Another street 5");

    }
}