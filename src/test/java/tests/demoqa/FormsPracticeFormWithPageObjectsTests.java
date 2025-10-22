package tests.demoqa;

import org.junit.jupiter.api.Test;
import pages.FormsPracticeFormPage;
import pages.components.ResultsFormTableComponent;

public class FormsPracticeFormWithPageObjectsTests extends TastBase {

    FormsPracticeFormPage registration = new FormsPracticeFormPage();
    ResultsFormTableComponent results = new ResultsFormTableComponent();

    @Test
    void successFillingFormTest() {
        registration.openPage()
                .removingBanner()
                .setFirstName("Don")
                .setLastName("Carlione")
                .setUserEmail("Carl@mail.ru")
                .setGender("Male")
                .setUserNumber("9001112222")
                .setDateOfBirth("July", "2008","30")
                .setSubject("Maths")
                .setHobbies("Sports")
                .setUploadPicture("1.png")
                .setCurrentAddress("Lesovskay 3")
                .setState("NCR")
                .setCity("Noida")
                .submitButton()

                .chechHeader("Thanks for submitting the form")
                .checkResult("Student name", "Don Carlione")
                .checkResult("Student Email", "Carl@mail.ru")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9001112222")
                .checkResult("Date of Birth", "30 July,2008")
                .checkResult("Subjects", "Maths")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "1.png")
                .checkResult("Address", "Lesovskay 3")
                .checkResult("State and City", "NCR Noida");
        results.closeButton();
    }

    @Test
    void MinimumDataToFillFormTest() {
        registration.openPage()
                .setFirstName("Don")
                .setLastName("Carlione")
                .setGender("Male")
                .setUserNumber("9001112222")
                .submitButton()

                .checkResult("Student name", "Don Carlione")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9001112222");
        results.closeButton();
    }

    @Test
    void FildLastNameIsNotInTest() {
        registration.openPage()
                .setFirstName("Don")
                .setLastName("")
                .setGender("Male")
                .setUserNumber("9001112222")
                .submitButton()

                .shouldNotBeResultTable();
    }
}
