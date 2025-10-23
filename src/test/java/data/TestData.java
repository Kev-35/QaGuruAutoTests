package data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {

    static Faker faker = new Faker(new Locale("en-GB"));

    public static final String
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = faker.options().option("Male", "Female", "Other"),
            phoneNumber = faker.phoneNumber().subscriberNumber(10),
            month = faker.options().option("January", "February", "March",
                                                    "April", "May", "June", "July", "August",
                                                    "September", "October", "November", "December"),
            year = String.format("%s", faker.number().numberBetween(1950, 2024)),
            day = String.format("%s", faker.number().numberBetween(1, 28)),
            subject = faker.options().option("Hindi", "English", "Maths", "Physics", "Chemistry", "Biology"),
            hobbies = faker.options().option("Sports", "Reading", "Music"),
            uploadPicture = faker.options().option("1.png", "2.png", "3.png"),
            currentAddress = faker.address().fullAddress(),
            state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            city = getCity(state);

    public static String getCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            default -> "null";
        };
    }

}
