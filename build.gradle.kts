buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.kotlin)
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val clean by tasks.registering(Delete::class) {
    delete(rootProject.buildDir)
}
