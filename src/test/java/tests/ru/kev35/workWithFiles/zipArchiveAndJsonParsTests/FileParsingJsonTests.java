package tests.ru.kev35.workWithFiles.zipArchiveAndJsonParsTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.ru.kev35.workWithFiles.zipArchiveAndJsonParsTests.model.Person;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class FileParsingJsonTests {


    private final ClassLoader cl = FileParsingJsonTests.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Проверка состава данных файла person.json")
    public void checkingDataInPersonJsonFile() throws Exception {

        try (InputStream is = cl.getResourceAsStream("person.json"))
        {
            Person person = objectMapper.readValue(is, Person.class);

            assertAll(
                    () -> assertEquals("Иван", person.getName()),
                    () -> assertEquals(30, person.getAge()),
                    () -> assertFalse(person.getMarried()),
                    () -> assertEquals(
                            Arrays.asList("чтение", "путешествия"),
                            Arrays.asList(person.getHobbies())));
        }
    }
}