plugins {
    application
}

application {
    mainClass.set("com.lfonta.littleowl.Main")
}

dependencies {
//    implementation(project(":core"))
    implementation(project(":persistence"))
    implementation(libs.jda)
    implementation(libs.logback.classic)
    implementation(libs.reflections)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
}
