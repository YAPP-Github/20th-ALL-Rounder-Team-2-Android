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
    implementation(libs.coroutines.core)

    testImplementation(libs.bundles.junit5)
    testImplementation(libs.truth)
    testImplementation(libs.mockk.core)
    testImplementation(libs.coroutines.test)
}
