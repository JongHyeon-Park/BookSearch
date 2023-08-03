import java.io.FileInputStream
import java.util.*

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.secrets)
    alias(libs.plugins.kotlin.serialization)
}

android {
    val propFile = rootProject.file("build.properties")
    val properties = Properties().apply { load(FileInputStream(propFile))}

    namespace = "com.bradpark.network"
    compileSdk = properties.getProperty("compileSdk").toInt()

    defaultConfig {
        minSdk = properties.getProperty("minSdk").toInt()

        testInstrumentationRunner = "com.bradpark.testing.BookTestRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    implementation(libs.androidx.core.ktx)

    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.retrofit.serialization)
    implementation(libs.okhttp3.logging.interceptor)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit4)
    kaptAndroidTest(libs.hilt.compiler)

    androidTestImplementation(project(":core:testing"))

    androidTestImplementation(libs.hilt.android.testing)

    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}