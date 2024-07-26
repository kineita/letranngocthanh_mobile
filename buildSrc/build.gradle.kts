plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.2.0")
    implementation(kotlin("gradle-plugin", "1.9.0"))
}