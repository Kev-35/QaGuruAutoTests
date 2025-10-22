package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ResultsFormTableComponent {

    private final SelenideElement

            header = $("#example-modal-sizes-title-lg"),
            resultsOfTable = $(".table-responsive"),
            table = $(".modal-content"),
            closeButton = $("#closeLargeModal");

    public void checkHaveHeaderH1(String value) {
        header.shouldHave(text(value));
    }

    public void resultsTable(String key, String value){
        resultsOfTable.shouldHave(text(key)).parent().shouldHave(text(value));
    }

    public void shouldNotBeAppearTable(){
        table.shouldNot(appear);
    }

    public void closeButton(){
        closeButton.click();
    }
}