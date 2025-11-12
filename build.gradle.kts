plugins {
    id("java")
}

group = "ru.kev35"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("com.codeborne:selenide:7.9.4")
    testImplementation("com.codeborne:pdf-test:1.5.0")
    testImplementation("com.codeborne:xls-test:1.4.3")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("com.github.javafaker:javafaker:1.0.2")

    testImplementation("com.opencsv:opencsv:5.12.0")

    testImplementation("com.google.code.gson:gson:2.13.2")

    testImplementation("com.fasterxml.jackson.core:jackson-core:2.16.0")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
    testImplementation("com.fasterxml.jackson.core:jackson-annotations:2.16.0")

    implementation("org.slf4j:slf4j-api:2.0.7")
}

tasks.test {
    useJUnitPlatform()
}