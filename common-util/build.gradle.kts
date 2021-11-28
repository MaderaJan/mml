plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
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
    // COROUTINES
    api(Libs.Coroutines.core)

    // KOTLIN
    api(Libs.Kotlin.stdlib)

    // KOIN
    api(Libs.Koin.koinCore)
    api(Libs.Koin.koinAndroid)

    // OTHERS
    api(Libs.timber)
}