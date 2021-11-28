plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.navigationSafeArgs)
    id(Plugins.gradleVersions) version Plugins.gradleVersionsVersion
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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":common-ui"))
    implementation(project(":common-resources"))
    implementation(project(":common-util"))
    implementation(project(":data"))
    implementation(project(":navigation"))

    api(Libs.Spotify.auth)
    api(Libs.Spotify.browser)

    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.Koin.koinCore)
    implementation(Libs.Koin.koinAndroid)
}