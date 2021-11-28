plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.gradleVersions) version Plugins.gradleVersionsVersion
}

android {
    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
    }
}

dependencies {
    implementation(project(":common-util"))

    implementation(Libs.Room.roomRunTime)
    implementation(Libs.Room.roomKtx)

    kapt(Libs.Room.roomCompiler)

    testImplementation(Libs.Room.roomTesting)
}