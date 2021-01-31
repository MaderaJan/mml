package cz.maderajan.mml.data.di

import cz.maderajan.mml.data.data.mapper.album.AlbumResponseToAlbumMapper
import cz.maderajan.mml.data.data.mapper.artist.ArtistResponseToArtistMapper
import org.koin.dsl.module

object MapperModule {

    internal val module = module {
        factory { ArtistResponseToArtistMapper() }
        factory { AlbumResponseToAlbumMapper(get()) }
    }
}