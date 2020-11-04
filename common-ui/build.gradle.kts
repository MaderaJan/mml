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
    implementation(Libs.Kotlin.stdlib)

    api(Libs.Ui.appCompat)
    api(Libs.Ui.material)
    api(Libs.Ui.constraint)
    api(Libs.LifeCycle.runTime)
    api(Libs.LifeCycle.runTime)
    implementation("androidx.lifecycle:lifecycle-common-java8:2.2.0")
    implementation("androidx.activity:activity-ktx:1.1.0")

    api(Libs.Koin.koinCore)
    api(Libs.Koin.koinExt)
    api(Libs.Koin.koinAndroid)
    api(Libs.Koin.viewModel)
}