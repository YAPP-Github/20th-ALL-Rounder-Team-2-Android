plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "kr.co.knowledgerally"

        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME
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
    implementation(project(Modules.CORE_EXCEPTION))

    implementation(platform("com.google.firebase:firebase-bom:30.2.0"))

    implementation(libs.stetho.core)
    implementation(libs.stetho.okhttp3)

    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")

    implementation(libs.coroutines.android)
    implementation(libs.material)

    implementation(libs.kakao.user)

    implementation(libs.hilt.android)
    implementation(libs.spectrum)
    kapt(libs.hilt.compiler)

    compileOnly(libs.okhttp.logging.interceptor)
}
