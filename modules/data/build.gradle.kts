plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}
