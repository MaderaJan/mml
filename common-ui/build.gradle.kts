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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":common-util"))
    implementation(project(":common-resources"))

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
    api(Libs.Koin.koinExt)
    api(Libs.Koin.koinAndroid)
    api(Libs.Koin.viewModel)

    api(Libs.NavigationComponent.fragment)
    api(Libs.NavigationComponent.uiKtx)
}