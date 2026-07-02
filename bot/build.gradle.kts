plugins {
    application
}

application {
    mainClass.set("online.theowlery.Main")
}

dependencies {

    implementation("net.dv8tion:JDA:6.3.2")
    implementation("ch.qos.logback:logback-classic:1.5.34")
    implementation("io.github.cdimascio:dotenv-java:3.2.0")
    implementation("org.reflections:reflections:0.10.2")

    compileOnly("org.projectlombok:lombok:1.18.46")
    annotationProcessor("org.projectlombok:lombok:1.18.46")

    testCompileOnly("org.projectlombok:lombok:1.18.46")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.46")
}
