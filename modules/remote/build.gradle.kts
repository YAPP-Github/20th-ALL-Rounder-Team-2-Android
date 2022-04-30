plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(project(Modules.DATA))
    
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}