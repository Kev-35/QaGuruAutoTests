package tests.demoqa;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ElementsWebTablesParametrizedTests extends TastBase {


    @DisplayName("Успешное добавление работника в таблицу")
    @Tag("WebTest")
    @ParameterizedTest
    @ValueSource(strings = {"Вася", "Антон", "Коля"})
    public void successAddEmployeeToTable(String name){


    }

}
