import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

val path = "${rootProject.projectDir.absolutePath}/local.properties"
val properties = loadProperties(path)
val KAKAO_API_KEY: String by properties

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "kr.co.knowledgerally"

        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        buildConfigField("String", "KAKAO_API_KEY", KAKAO_API_KEY)
        manifestPlaceholders["KAKAO_API_KEY"] = KAKAO_API_KEY
    }

    signingConfigs {
        debug {
            storeFile = file("$rootDir/settings/debug.keystore")
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("staging") {
            initWith(release)
            isDebuggable = true
            matchingFallbacks += "release"
            applicationIdSuffix = ".staging"
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.DATA))
    implementation(project(Modules.LOCAL))
    implementation(project(Modules.REMOTE))
    implementation(project(Modules.LOG))

    implementation(libs.coroutines.android)

    implementation(libs.kakao.user)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    compileOnly(libs.okhttp.logging.interceptor)
}
