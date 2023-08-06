plugins {
    id("java")
}

group = "me.raduserban11"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-inline:4.0.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.0.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.10.0")
    implementation ("org.apache.commons:commons-lang3:3.6")
    implementation("org.apache.commons:commons-text:1.10.0")
}

tasks.test {
    useJUnitPlatform()
}