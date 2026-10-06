allprojects {
    group = "com.lfonta.littleowl"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

val devEnv: Map<String, String> = rootProject.file(".env").takeIf { it.exists() }
    ?.readLines()
    ?.map { it.trim() }
    ?.filter { it.isNotEmpty() && !it.startsWith("#") && "=" in it }
    ?.associate { it.substringBefore("=").trim() to it.substringAfter("=").trim().removeSurrounding("\"") }
    ?: emptyMap()
extra["devEnv"] = devEnv

subprojects {
    apply(plugin = "java")

    extensions.configure<JavaPluginExtension> {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    dependencies {
        "testImplementation"(platform(rootProject.libs.junit.bom))
        "testImplementation"("org.junit.jupiter:junit-jupiter")
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test>().configureEach { useJUnitPlatform() }
    tasks.withType<JavaExec>().configureEach {
        workingDir(rootProject.projectDir)
        devEnv.forEach { (k, v) -> if (System.getenv(k) == null) environment(k, v) }
    }
}

// ---- Base de datos de desarrollo ----
tasks.register<Exec>("dbUp") {
    group = "database"
    description = "Levanta PostgreSQL de desarrollo con Docker Compose y espera a que esté healthy."
    commandLine("docker", "compose", "up", "-d", "--wait")
}

tasks.register<Exec>("dbDown") {
    group = "database"
    description = "Detiene PostgreSQL de desarrollo (conserva los datos)."
    commandLine("docker", "compose", "down")
}

tasks.register<Exec>("dbReset") {
    group = "database"
    description = "Detiene PostgreSQL y BORRA su volumen (todos los datos de desarrollo)."
    commandLine("docker", "compose", "down", "-v")
}