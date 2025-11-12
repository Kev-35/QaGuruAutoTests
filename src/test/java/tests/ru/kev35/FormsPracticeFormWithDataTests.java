package tests.ru.kev35;

import org.junit.jupiter.api.Test;
import pages.FormsPracticeFormPage;
import pages.components.ResultsFormTableComponent;

import static data.TestData.*;

public class FormsPracticeFormWithDataTests extends TestBase {
    FormsPracticeFormPage registration = new FormsPracticeFormPage();
    ResultsFormTableComponent results = new ResultsFormTableComponent();

    @Test
    void successFillingFormTest() {
        registration.openPage()
                .removingBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth(month, year, day)
                .setSubject(subject)
                .setHobbies(hobbies)
                .setUploadPicture(uploadPicture)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submitButton()

                .chechHeader("Thanks for submitting the form")
                .checkResult("Student name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", uploadPicture)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);
        results.closeButton();
    }

    @Test
    void MinimumDataToFillFormTest() {
        registration.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submitButton()

                .checkResult("Student name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber);
        results.closeButton();
    }

    @Test
    void FildLastNameIsNotInTest() {
        registration.openPage()
                .setFirstName(firstName)
                .setLastName("")
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submitButton()

                .shouldNotBeResultTable();
    }
}
