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

    namespace = "com.bradpark.detail"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))

    implementation(libs.androidx.core.ktx)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose)

    implementation(libs.bundles.coil)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.hilt.ext.navigation.compose)
    kapt(libs.hilt.ext.compiler)

    implementation(libs.lottie.compose)

    implementation(libs.bundles.retrofit.serialization)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.androidx.compose.test)

    debugImplementation(libs.bundles.androidx.compose.debug)
}