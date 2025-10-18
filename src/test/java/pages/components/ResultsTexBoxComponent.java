package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTexBoxComponent {

    public void TexBoxComponent(String key, String value){
        $("#output").shouldHave(text(key), text(value));
    }
}
