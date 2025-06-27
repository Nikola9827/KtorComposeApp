plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.21"
}

kotlin {
    sourceSets.all{
        languageSettings.optIn("kotlinx.serialization.ExperimentalSerializationApi")
    }
}

android {
    namespace = "com.example.ktorcomposeapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ktorcomposeapp"
        minSdk = 24
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
val ktorVersion = "2.3.5"
val serializationVersion = "1.6.3"

dependencies {

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion") // or android if you prefer
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Ktor Client (Android Engine)
    implementation("io.ktor:ktor-client-android:2.3.7")
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.kotlinx.coroutines.android)

// JSON serialization
    implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")

// Coroutines (already usually included, but just in case)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    // Koin for Android
    implementation("io.insert-koin:koin-android:3.5.3")

// Koin ViewModel support
    implementation("io.insert-koin:koin-android:3.5.3")

// Optional: for Compose if needed later
    implementation("io.insert-koin:koin-androidx-compose:3.5.3")

}