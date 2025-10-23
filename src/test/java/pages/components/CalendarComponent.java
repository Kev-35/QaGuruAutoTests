package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent{

    private final SelenideElement
            choiceMonth = $(".react-datepicker__month-select"),
            choiceYear = $(".react-datepicker__year-select");

    public void setDate(String month, String year, String day){
        choiceMonth.selectOption(month);
        choiceYear.selectOption(year);
        String choiceDay = ".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)";
        $(String.format(choiceDay, day)).click();

    }
}
