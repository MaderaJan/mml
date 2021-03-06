import org.gradle.api.Plugin
import org.gradle.api.Project

object Libs : Plugin<Project> {

    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val dataStorePreferences = "androidx.datastore:datastore-preferences:1.0.0-alpha02"

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:1.4.10"
    }

    object Room {
        const val roomRunTime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val roomTesting = "androidx.room:room-testing:${Versions.room}"
    }

    object Koin {
        const val koinCore = "org.koin:koin-core:${Versions.koin}"
        const val koinExt= "org.koin:koin-core-ext:${Versions.koin}"
        const val koinTesting =  "org.koin:koin-test:${Versions.koin}"
        const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
        const val viewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.7.2"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:2.9.0"
    }

    object Ui {
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val material = "com.google.android.material:material:1.2.1"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val activityX = "androidx.activity:activity-ktx:1.1.0"
        const val coil = "io.coil-kt:coil:1.1.1"
    }

    object NavigationComponent {
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"
    }

    object Spotify {
        const val auth = "com.spotify.android:auth:1.1.0"
    }

    object LifeCycle {
        const val runTime = "androidx.lifecycle:lifecycle-runtime:2.2.0"
        const val common = "androidx.lifecycle:lifecycle-common-java8:2.2.0"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1"
    }

    override fun apply(target: Project) {}
}