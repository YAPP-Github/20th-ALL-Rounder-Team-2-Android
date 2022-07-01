buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.kotlin)
        classpath(libs.hilt.gradle)
        classpath(libs.google.services)
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.1")
        classpath(libs.google.secrets.gradle)
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

allprojects {
    apply {
        plugin("org.jlleitschuh.gradle.ktlint")
    }
    afterEvaluate {
        ktlint {
            verbose.set(true)

            if (isAndroidProject) {
                android.set(true)
            }
        }
    }
}

val clean by tasks.registering(Delete::class) {
    delete(rootProject.buildDir)
}
