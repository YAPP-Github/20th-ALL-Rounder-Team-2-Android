plugins {
    kotlin("jvm")
}

tasks {
    test {
        useJUnitPlatform()
    }
}

dependencies {
    implementation(libs.javax.inject)

    testImplementation(libs.bundles.junit5)
    testImplementation(libs.truth)
    testImplementation(libs.mockk.core)
    testImplementation(libs.coroutines.test)
}
