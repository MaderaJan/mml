plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.gradleVersions) version Plugins.gradleVersionsVersion
}

android {
    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
    }
}

dependencies {
    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.Room.roomRunTime)
    kapt(Libs.Room.roomCompiler)
    implementation(Libs.Room.roomKtx)
    testImplementation(Libs.Room.roomTesting)

    implementation(Libs.Koin.koinCore)
    implementation(Libs.Koin.koinAndroid)
}