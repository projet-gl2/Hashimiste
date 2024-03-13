plugins {
    id("java")
    id("application")
    id("org.sonarqube") version "3.5.0.2730"
}

group = "fr.hashimiste"
version = "1.0-SNAPSHOT"

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.7.2")
    implementation("ch.obermuhlner:jshell-scriptengine:1.1.0")
    implementation("org.python:jython:2.7.2")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

application {
    mainClass.set("fr.hashimiste.impl.Main")
}

sonar {
    properties {
        property("sonar.projectKey", "projet-gl2_Hashimiste_AY4o2rljbqkgiLWt9-aA")
    }
}
