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
}

dependencies {
    implementation(project(":common-util"))

    api(Libs.NavigationComponent.fragment)
    api(Libs.NavigationComponent.uiKtx)
    api(Libs.NavigationComponent.dynamicFeatures)

    api(Libs.Koin.navigation)
}