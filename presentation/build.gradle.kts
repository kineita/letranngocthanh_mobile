plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.letranngocthanh.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":domain"))

    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)
    implementation(Deps.coroutinesAndroid)
    implementation(Deps.lifecycleViewModelKtx)
    implementation(Deps.composeRuntime)
    implementation(Deps.composeUi)
    implementation(Deps.composeMaterial)
    implementation(Deps.koinCore)
    implementation(Deps.koinAndroid)
    implementation(Deps.koinAndroidCompose)
    implementation(Deps.koinAndroidComposeNavigation)
    implementation(Deps.koinCompose)
    implementation(Deps.koinCoroutine)
    implementation(Deps.timber)

    testImplementation(Deps.mockk)
    testImplementation(Deps.junit)
    testImplementation(Deps.testing)
    testImplementation(Deps.coroutinesTest)
    testImplementation(Deps.coreTesting)
    testImplementation(Deps.jupiterApi)
    testImplementation(Deps.jupiterEngine)
    testImplementation(Deps.slf4j)
}