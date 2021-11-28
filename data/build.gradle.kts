plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.gradleVersions) version Plugins.gradleVersionsVersion
}

android {
    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":common-util"))
    implementation(project(":webservice"))
    implementation(project(":database"))

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Retrofit.retrofit)

    api(Libs.dataStorePreferences)

    implementation(Libs.Koin.koinCore)
    implementation(Libs.Koin.koinAndroid)
}