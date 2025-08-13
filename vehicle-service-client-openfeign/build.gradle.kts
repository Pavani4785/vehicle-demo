plugins {
    alias(libs.plugins.cafe)
    alias(libs.plugins.cafeOpenapiClient)
}

dependencies {
    api(projects.vehicleServiceDomain)
    api(libs.cafeStarterFeign)

    /* FIXME: Client code does not generate correctly in CI, but does locally.
     *  When it generates correctly in CI, remove this. */
    api("org.springframework.boot:spring-boot-starter-oauth2-client")
}
