plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
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

    implementation(Libs.Koin.koinCore)
    implementation(Libs.Koin.koinExt)
    implementation(Libs.Koin.koinAndroid)
}