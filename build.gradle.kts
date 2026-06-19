plugins {
    id("java")
}

allprojects {
    group = "online.theowlery"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {

    apply(plugin = "java")

    java {
        toolchain{
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }
}