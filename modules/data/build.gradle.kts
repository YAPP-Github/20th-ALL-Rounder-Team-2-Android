plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(project(Modules.DOMAIN))

    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}
