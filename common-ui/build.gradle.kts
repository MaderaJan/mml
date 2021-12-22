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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    api(project(":common-util"))
    api(project(":common-resources"))
    api(project(":data"))
    api(project(":navigation"))

    api(Libs.Ui.constraint)
    api(Libs.Ui.coil)
    api(Libs.Ui.coilCompose)
    implementation(Libs.Ui.activityX)

    api(Libs.LifeCycle.runTime)
    api(Libs.LifeCycle.common)

    api(Libs.NavigationComponent.fragment)
    api(Libs.NavigationComponent.uiKtx)
    api(Libs.NavigationComponent.dynamicFeatures)

    api(Libs.Compose.runTime)
    api(Libs.Compose.ui)
    api(Libs.Compose.foundation)
    api(Libs.Compose.foundationLayout)
    api(Libs.Compose.material)
    api(Libs.Compose.liveData)
    api(Libs.Compose.uiTooling)
    api(Libs.Compose.themeLoader)
    api(Libs.Compose.constraintLayout)
}