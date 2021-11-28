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