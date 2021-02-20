package cz.maderajan.mml.data.di

import cz.maderajan.mml.data.TokenRepository
import cz.maderajan.mml.data.spotify.SpotifyRepository
import org.koin.dsl.module

object RepositoryModule {

    internal val module = module {
        single { SpotifyRepository(get(), get(), get()) }
        single { TokenRepository(get()) }
    }
}