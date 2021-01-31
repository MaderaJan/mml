package cz.maderajan.mml.webservice.di

import cz.maderajan.mml.webservice.BuildConfig
import cz.maderajan.mml.webservice.SpotifyApi
import cz.maderajan.mml.webservice.util.OkHttpCreator
import cz.maderajan.mml.webservice.util.RetrofitCreator
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
object WebServiceModule {

    val module = module {
        single { OkHttpCreator().create() }

        single { RetrofitCreator.create<SpotifyApi>(BuildConfig.SPOTIFY_API_URL, get()) }
    }
}