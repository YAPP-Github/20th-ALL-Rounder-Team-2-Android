plugins {
    kotlin("jvm")
}

tasks {
    test {
        useJUnitPlatform()
    }
}

dependencies {
    testImplementation(libs.bundles.junit5)
    testImplementation(libs.truth)
    testImplementation(libs.mockk.core)
}
