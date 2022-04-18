package cz.maderajan.mml.data.di

import cz.maderajan.mml.data.repository.album.AlbumRepository
import cz.maderajan.mml.data.repository.auth.TokenRepository
import cz.maderajan.mml.data.repository.spotify.SpotifyRepository
import org.koin.dsl.module

object RepositoryModule {

    internal val module = module {
        factory { SpotifyRepository(get(), get(), get()) }
        factory { AlbumRepository(get(), get()) }
        factory { TokenRepository(get()) }
    }
}