import java.io.FileInputStream
import java.util.*

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    val propFile = rootProject.file("build.properties")
    val properties = Properties().apply { load(FileInputStream(propFile))}
    namespace = "com.bradpark.testing"
    compileSdk = properties.getProperty("compileSdk").toInt()

    defaultConfig {
        minSdk = properties.getProperty("minSdk").toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.hilt.android)
    kaptAndroidTest(libs.hilt.compiler)

    testImplementation(libs.junit4)
    implementation(libs.hilt.android.testing)
    implementation(libs.test.runner)

    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
}