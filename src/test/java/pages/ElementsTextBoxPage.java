package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.ResultsTexBoxComponent;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ElementsTextBoxPage {

    private final SelenideElement fullName = $("#userName"),
            email = $("#userEmail"),
            currentAddress = $("#currentAddress"),
            permanentAddress = $("#permanentAddress"),
            submitButton = $("#submit");

    ResultsTexBoxComponent textBoxResults = new ResultsTexBoxComponent();

    public ElementsTextBoxPage openPage(){
        open("/text-box");
        return this;
    }
    public ElementsTextBoxPage fullName(String value){
        fullName.setValue(value);
        return this;
    }
    public ElementsTextBoxPage email(String value){
        email.setValue(value);
        return this;
    }
    public ElementsTextBoxPage currentAddress(String value){
        currentAddress.setValue(value);
        return this;
    }
    public ElementsTextBoxPage permanentAddress(String value){
        permanentAddress.setValue(value);
        return this;
    }
    public ElementsTextBoxPage submitButton(){
        submitButton.click();
        return this;
    }
    public ElementsTextBoxPage checkResult(String key, String value){
        textBoxResults.TexBoxComponent(key, value);
        return this;
    }


}
