package pages.components;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ResultsFormTableComponent {
    public void resultsTable(String key, String value){
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(key)).parent().shouldHave(text(value));
    }
    public void shouldNotBeAppearTable(){
        $(".modal-content").shouldNot(appear);
    }


}
