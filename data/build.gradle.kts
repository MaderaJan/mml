plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.gradleVersions) version Plugins.gradleVersionsVersion
}

android {
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
    }
}

dependencies {
    implementation(project(":common-util"))
    implementation(project(":webservice"))
    implementation(project(":database"))

    implementation(Libs.Retrofit.retrofit)

    api(Libs.dataStorePreferences)
}