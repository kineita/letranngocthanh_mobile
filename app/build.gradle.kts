plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.letranngocthanh.mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.letranngocthanh.mobile"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Deps.core)
    implementation(Deps.material)
    implementation(Deps.composeRuntime)
    implementation(Deps.material3)
    implementation(Deps.composeUi)
    implementation(Deps.composeMaterial)
    implementation(Deps.composeUiTooling)
    implementation(Deps.koinCore)
    implementation(Deps.koinAndroid)
    implementation(Deps.koinAndroidCompose)
    implementation(Deps.koinAndroidComposeNavigation)
    implementation(Deps.koinCompose)
    implementation(Deps.koinCoroutine)
    implementation(Deps.navFragment)
    implementation(Deps.navUi)
    implementation(Deps.lifecycleViewModelKtx)
    implementation(Deps.timber)
    implementation(Deps.coil)

    androidTestImplementation(Deps.composeUiTestJunit4)
    androidTestImplementation(Deps.composeUiTestManifest)
    androidTestImplementation(Deps.espressoCore)
    androidTestImplementation(Deps.koinTest)
    androidTestImplementation(Deps.koinJunitTest)
    androidTestImplementation(Deps.androidxTestExtJunit)
    androidTestImplementation(Deps.mockkAndroid)
    androidTestImplementation(Deps.testing)
    androidTestImplementation(Deps.coreTesting)
    androidTestImplementation(Deps.koinAndroidCompose)
}