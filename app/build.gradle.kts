plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Firebase plugin
    id("com.google.gms.google-services") version "4.4.2"

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.raionapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.raionapp"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}


dependencies {
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Firebase dependencies Bom
    implementation(platform("com.google.firebase:firebase-bom:33.10.0"))

    // ObserveAsState() MVVM
    implementation("androidx.compose.runtime:runtime-livedata:1.0.5")

    // Firebase Native Authentication
    implementation("com.google.firebase:firebase-auth")

    // Firebase Google Authentication
    implementation("com.google.android.gms:play-services-auth:21.2.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.8")

    // Credential Manager
    implementation("androidx.credentials:credentials:1.5.0-rc01")
    implementation("androidx.credentials:credentials-play-services-auth:1.5.0-rc01")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // Coil Dependency for Image Loading
    implementation("io.coil-kt:coil-compose:2.2.2")
    // Text Fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.0.0")

    // Exoplayer
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.exoplayer.dash)
    implementation(libs.androidx.media3.ui)

    // Dagger-Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

}

kapt {
    correctErrorTypes = true
}