package tests.ru.kev35.workWithFiles.zipArchiveAndJsonParsTests.model;

public class Person {

    private String name;
    private Integer age;
    private Boolean married;
    private String[] hobbies;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getMarried() {
        return married;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

}