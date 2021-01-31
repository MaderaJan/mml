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
    implementation(project(":common-util"))

    implementation(Libs.Kotlin.stdlib)

    api(Libs.Ui.appCompat)
    api(Libs.Ui.material)
    api(Libs.Ui.constraint)
    implementation(Libs.Ui.activityX)

    api(Libs.LifeCycle.runTime)
    api(Libs.LifeCycle.common)

    api(Libs.Coroutines.core)

    api(Libs.Koin.koinCore)
    api(Libs.Koin.koinExt)
    api(Libs.Koin.koinAndroid)
    api(Libs.Koin.viewModel)

    api(Libs.NavigationComponent.fragment)
    api(Libs.NavigationComponent.uiKtx)
}