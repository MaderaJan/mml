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
    api(project(":common-util"))
    api(project(":common-resources"))
    api(project(":data"))
    api(project(":navigation"))

    api(Libs.Ui.constraint)
    api(Libs.Ui.coil)
    implementation(Libs.Ui.activityX)

    api(Libs.LifeCycle.runTime)
    api(Libs.LifeCycle.common)

    api(Libs.NavigationComponent.fragment)
    api(Libs.NavigationComponent.uiKtx)
    api(Libs.NavigationComponent.dynamicFeatures)
}