plugins {
    kotlin("jvm") version "1.9.23"
    id("org.openjfx.javafxplugin") version "0.0.13"
}

group = "com.letter.learning"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val mockkVersion = "1.13.13"
val assertjVersion = "3.26.3"
val tornadoVersion = "1.7.20"
val jacksonVersion = "2.14.2"

javafx {
    version = "17.0.2"
    modules = listOf("javafx.controls", "javafx.graphics")
}

dependencies {
    implementation("no.tornado:tornadofx:$tornadoVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
