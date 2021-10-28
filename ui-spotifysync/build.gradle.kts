plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.navigationSafeArgs)
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

    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.Koin.koinCore)
    implementation(Libs.Koin.koinExt)
    implementation(Libs.Koin.koinAndroid)
    implementation("com.reddit:indicator-fast-scroll:1.3.0")
}