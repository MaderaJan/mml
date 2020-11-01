package cz.maderajan.mml.webservice.di

import cz.maderajan.mml.webservice.SpotifyApi
import cz.maderajan.mml.webservice.util.OkHttpCreator
import cz.maderajan.mml.webservice.util.RetrofitCreator
import org.koin.dsl.module

val module = module {
    single { OkHttpCreator().create() }
    single { RetrofitCreator.create<SpotifyApi>("", get()) }
}