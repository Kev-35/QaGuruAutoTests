package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultsFormTableComponent;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class FormsPracticeFormPage {

    private final SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            userEmail = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumber = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            state = $("#state"),
            selectState = $("#react-select-3-input"),
            city = $("#city"),
            selectCity = $("#react-select-4-input"),
            submitButton = $("#submit"),
            closeButton = $("#closeLargeModal");

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultsFormTableComponent modalDialog = new ResultsFormTableComponent();

    public FormsPracticeFormPage openPage(){
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    public FormsPracticeFormPage setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }
    public FormsPracticeFormPage setLastName(String value){
        lastName.setValue(value);
        return this;
    }
    public FormsPracticeFormPage setUserEmail(String value){
        userEmail.setValue(value);
        return this;
    }
    public FormsPracticeFormPage setGender(String value){
        genderWrapper.$(byText(value)).click();
        return this;
    }
    public FormsPracticeFormPage setUserNumber(String value){
        userNumber.setValue(value);
        return this;
    }
    public FormsPracticeFormPage setDateOfBirth(String month, String year, String day ){
        calendarInput.click();
        calendarComponent.setDate(month, year, day);
        return this;
    }
    public FormsPracticeFormPage setSubject(String value){
        subjectsInput.setValue(value).pressEnter();
        return this;
    }
    public FormsPracticeFormPage setHobbies(String value){
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }
    public FormsPracticeFormPage setUploadPicture(String path){
        uploadPicture.uploadFromClasspath(path);
        return this;
    }
    public FormsPracticeFormPage setCurrentAddress(String value){
        currentAddress.setValue(value);
        return this;
    }
    public FormsPracticeFormPage setState(String value){
        state.click();
        selectState.setValue(value).pressEnter();
        return this;
    }
    public FormsPracticeFormPage setCity(String value){
        city.click();
        selectCity.setValue(value).pressEnter();
        return this;
    }
    public FormsPracticeFormPage submitButton(){
        submitButton.click();
        return this;
    }
    public FormsPracticeFormPage checkResult(String key, String value) {
        modalDialog.resultsTable(key, value);
        return this;
    }
    public FormsPracticeFormPage closeButton(){
        closeButton.click();
        return this;
    }
    public FormsPracticeFormPage shouldNotBeResultTable(){
        modalDialog.shouldNotBeAppearTable();
        return this;
    }
}
