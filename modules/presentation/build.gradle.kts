plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.ui.text.ExperimentalTextApi",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
        )
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    testOptions {
        unitTests.all { options -> options.useJUnitPlatform() }
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.LOG))
    implementation(project(Modules.CORE_EXCEPTION))

    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.compose.ui.core)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.navigation)

    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.activity.compose)

    implementation(libs.google.accompanist.systemuicontroller)
    implementation(libs.google.accompanist.navigation.animation)
    implementation(libs.google.accompanist.webview)
    implementation(libs.google.accompanist.pager.core)
    implementation(libs.google.accompanist.pager.indicators)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.coil.compose)
    implementation(libs.material)

    testImplementation(libs.junit4)
    testImplementation(libs.bundles.junit5)
    testImplementation(libs.truth)
    testImplementation(libs.mockk.core)
    testImplementation(libs.coroutines.test)

    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.customview.poolingcontainer)
    debugImplementation(libs.androidx.compose.ui.tooling.core)
}
