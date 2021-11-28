plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)

        buildConfigField("String", "SPOTIFY_API_URL", "\"https://api.spotify.com/v1/\"")
    }
}

dependencies {
    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.Retrofit.moshiConverter)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.loggingInterceptor)

    implementation(Libs.Koin.koinCore)
    implementation(Libs.Koin.koinAndroid)
}