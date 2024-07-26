object Versions {
    const val kotlin = "1.9.10"
    const val coroutines = "1.7.3"
    const val appCompat = "1.6.1"
    const val material = "1.9.0"
    const val constraintLayout = "2.1.4"
    const val mockk = "1.13.5"
    const val junit = "4.13.2"
    const val retrofit = "2.9.0"
    const val retrofitCoroutines = "0.9.2"
    const val okhttp = "4.9.3"
    const val androidxTestExtJunit = "1.1.5"
    const val lifecycle = "2.8.0"
    const val compose = "1.5.1" // Use the version compatible with your Compose setup
    const val composeUi = "1.5.1" // Ensure this matches your Compose version
}

object Deps {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val junit = "junit:junit:${Versions.junit}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitCoroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val androidxTestExtJunit = "androidx.test.ext:junit:${Versions.androidxTestExtJunit}"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.composeUi}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeUi}"
}