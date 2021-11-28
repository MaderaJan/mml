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
    implementation(project(":navigation"))

    implementation(Libs.Kotlin.stdlib)

    api(Libs.Ui.appCompat)
    api(Libs.Ui.material)
    api(Libs.Ui.constraint)
    api(Libs.Ui.coil)
    implementation(Libs.Ui.activityX)

    api(Libs.LifeCycle.runTime)
    api(Libs.LifeCycle.common)

    api(Libs.Coroutines.core)

    api(Libs.Koin.koinCore)
    api(Libs.Koin.koinAndroid)

    api(Libs.NavigationComponent.fragment)
    api(Libs.NavigationComponent.uiKtx)
    api(Libs.NavigationComponent.dynamicFeatures)
}