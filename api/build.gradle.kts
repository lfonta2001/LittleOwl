plugins {
    application
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

application {
    mainClass.set("com.lfonta.littleowl.ApiServer")
}

dependencies {
//    implementation(project(":core"))
    implementation(project(":persistence"))

    implementation(libs.springboot.starter.security)
    implementation(libs.springboot.starter.oauth2.resource)
    implementation(libs.springboot.starter.validation)
    implementation(libs.springboot.starter.webmvc)
    compileOnly(libs.lombok)

    developmentOnly(libs.springboot.devtools)
    annotationProcessor(libs.lombok)

    testImplementation(libs.springboot.test.starter.security)
    testImplementation(libs.springboot.test.starter.oauth2.resource)
    testImplementation(libs.springboot.test.starter.validation)
    testImplementation(libs.springboot.test.starter.webmvc)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
}
