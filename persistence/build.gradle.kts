buildscript {
    repositories { mavenCentral() }
    dependencies {
//      If this is changed also change in libs.versions.toml
        classpath("org.flywaydb:flyway-database-postgresql:13.9.0")
    }
}

plugins {
    `java-library`
    alias(libs.plugins.jooq.codegen)
    alias(libs.plugins.flyway)
}

@Suppress("UNCHECKED_CAST")
val devEnv = rootProject.extra["devEnv"] as Map<String, String>
fun env(name: String, default: String = ""): String =
    System.getenv(name) ?: devEnv[name] ?: default

val dbUrl = "jdbc:postgresql://${env("DATABASE_HOST", "localhost")}:" + "${env("DATABASE_PORT", "5432")}/${env("DATABASE_NAME")}"
val dbUser = env("DATABASE_USERNAME")
val dbPassword = env("DATABASE_PASSWORD")

dependencies {
//    api(project(":core"))

    implementation(libs.hikari)
    implementation(libs.jooq)
    implementation(libs.flyway.core)
    implementation(libs.flyway.postgresql)
    runtimeOnly(libs.postgresql)

    jooqCodegen(libs.postgresql)

    testImplementation(libs.testcontainers.postgresql)
    testImplementation(libs.testcontainers.junit)
}

sourceSets {
    main { java.srcDir("src/main/generated")}
}

val migrationDir = "filesystem:$projectDir/src/main/resources/db/migration"
// val seedDir = "filesystem:$projectDir/src/dev/resources/db/seed"

listOf("flywayMigrate", "jooqCodegen").forEach { taskName ->
    tasks.named(taskName) {
        doFirst {
            listOf("DATABASE_NAME", "DATABASE_USERNAME", "DATABASE_PASSWORD").forEach {
                check(env(it).isNotBlank()) { "The environment variable $it is missing."}
            }
        }
    }
}

flyway {
    url = dbUrl
    user = dbUser
    password = dbPassword
    locations = if (env("DATABASE_SEED", "false").toBoolean())
        arrayOf(migrationDir) else arrayOf(migrationDir)
}

jooq {
    configuration {
        jdbc {
            driver = "org.postgresql.Driver"
            url = dbUrl
            user = dbUser
            password = dbPassword
        }
        generator {
            database {
                name = "org.jooq.meta.postgres.PostgresDatabase"
                inputSchema = "public"
                excludes = "flyway_schema_history"
            }
            target {
                packageName = "com.lfonta.littleowl.persistence.generated"
                directory = "src/main/generated"
            }
        }
    }
}

tasks.named("jooqCodegen") { dependsOn("flywayMigrate")}