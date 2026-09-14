plugins {
    id("java")
}

allprojects {
    group = "com.lfonta.theowlery"
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

    dependencies {
        implementation("io.github.cdimascio:dotenv-java:3.2.0")

        testImplementation(platform("org.junit:junit-bom:6.0.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<JavaExec>().configureEach { workingDir(rootProject.projectDir) }

    tasks.test {
        useJUnitPlatform()
    }
}