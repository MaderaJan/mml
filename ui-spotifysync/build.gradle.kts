plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.navigationSafeArgs)
    id(Plugins.gradleVersions) version Plugins.gradleVersionsVersion
}

android {
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":common-ui"))

    api(Libs.Spotify.auth)
    api(Libs.Spotify.browser)
}