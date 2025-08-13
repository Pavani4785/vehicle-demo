plugins {
    alias(libs.plugins.cafe)
    alias(libs.plugins.cafeOpenapiService)
    alias(libs.plugins.springBoot)
}

dependencies {
    implementation(projects.vehicleServiceDomain)
    implementation(libs.cafeStarterMicroservice)
    implementation(libs.cafeStarterJpa)
    implementation(libs.cafeStarterOracle)

    testImplementation(libs.cafeStarterTest)
}

openApiService {
    apiPackage = "com.subaru.cv.vehicleservice"
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
