import java.io.FileInputStream
import java.util.*

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    val propFile = rootProject.file("build.properties")
    val properties = Properties().apply { load(FileInputStream(propFile))}

    namespace = "com.bradpark.data"
    compileSdk = properties.getProperty("compileSdk").toInt()

    defaultConfig {
        minSdk = properties.getProperty("minSdk").toInt()

        testInstrumentationRunner = "com.bradpark.testing.BookTestRunner"
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

    implementation(project(":core:domain"))
    implementation(project(":core:network"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.bundles.retrofit)

    implementation(libs.paging)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.hilt.android.testing)

    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
}