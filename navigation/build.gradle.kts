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
    implementation(project(":common-util"))
    implementation(project(":common-resources"))
    implementation(project(":data"))

    implementation(Libs.Kotlin.stdlib)

    api(Libs.NavigationComponent.fragment)
    api(Libs.NavigationComponent.uiKtx)
    api(Libs.NavigationComponent.dynamicFeatures)

    api(Libs.Koin.navigation)
}