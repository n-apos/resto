plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    kotlin("plugin.spring") version libs.versions.kotlin.get()
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

group = "com.workshops.resto"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.web)
    implementation(libs.spring.boot.security)
    implementation(libs.spring.boot.data.jpa)
    implementation(libs.io.jwt.api) // This is the jjwt-api

    // Add the JJWT implementation and a JSON parser (Jackson)
    runtimeOnly(libs.io.jwt.impl)
    runtimeOnly(libs.io.jwt.jackson)

    runtimeOnly(libs.springdoc)

    implementation(libs.h2)
    implementation(libs.postgres)
    implementation(libs.kotlinx.coroutines.core)
    implementation(kotlin("reflect"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}