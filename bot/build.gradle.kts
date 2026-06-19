plugins {
    application
}

application {
    mainClass.set("online.theowlery.Main")
}

dependencies {

    implementation("net.dv8tion:JDA:5.6.1")

    implementation("ch.qos.logback:logback-classic:1.5.18")

    implementation("io.github.cdimascio:java-dotenv:5.2.2")
}
