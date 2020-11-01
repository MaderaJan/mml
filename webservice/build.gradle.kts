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
}

dependencies {
    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.Retrofit.kotlinxSerialization)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.retrofitConverter)
    implementation(Libs.Retrofit.retrofitCoroutinesAdapter)
    implementation(Libs.Retrofit.loggingInterceptor)

    implementation(Libs.Koin.koinCore)
    implementation(Libs.Koin.koinExt)
    implementation(Libs.Koin.koinAndroid)
}