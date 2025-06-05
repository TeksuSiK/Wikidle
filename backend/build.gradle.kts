plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.6"
    application
}

group = "pl.teksusik.wikidle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:6.6.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("org.slf4j:slf4j-simple:2.0.16")

    implementation("org.jsoup:jsoup:1.20.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass = "pl.teksusik.wikidle.WikidleRunner"
}

tasks.test {
    useJUnitPlatform()
}