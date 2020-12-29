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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":common-ui"))
    implementation(project(":common-resources"))
    implementation(project(":common-util"))
    implementation(project(":data"))

    api(Libs.Spotify.auth)

    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.Koin.koinCore)
    implementation(Libs.Koin.koinExt)
    implementation(Libs.Koin.koinAndroid)

    implementation(Libs.NavigationComponent.fragment)
    implementation(Libs.NavigationComponent.uiKtx)
}