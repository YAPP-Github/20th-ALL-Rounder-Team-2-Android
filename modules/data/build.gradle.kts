plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(libs.coroutines.core)

    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}
