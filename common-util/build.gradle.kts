plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.gradleVersions) version Plugins.gradleVersionsVersion
}

android {
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
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