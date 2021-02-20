package cz.maderajan.mml.data.di

import cz.maderajan.mml.data.data.mapper.DataMapperFacade
import cz.maderajan.mml.data.data.mapper.album.AlbumEntityToAlbumMapper
import cz.maderajan.mml.data.data.mapper.album.AlbumResponseToAlbumMapper
import cz.maderajan.mml.data.data.mapper.album.AlbumToAlbumEntityMapper
import cz.maderajan.mml.data.data.mapper.artist.ArtistEntityToArtistMapper
import cz.maderajan.mml.data.data.mapper.artist.ArtistResponseToArtistMapper
import cz.maderajan.mml.data.data.mapper.artist.ArtistToArtistEntityMapper
import org.koin.dsl.module

object MapperModule {

    internal val module = module {
        // ARTIST
        factory { ArtistResponseToArtistMapper() }
        factory { ArtistToArtistEntityMapper() }
        factory { ArtistEntityToArtistMapper() }

        // ALBUM
        factory { AlbumResponseToAlbumMapper(get()) }
        factory { AlbumToAlbumEntityMapper() }
        factory { AlbumEntityToAlbumMapper(get()) }

        factory { DataMapperFacade(get(), get(), get(), get(), get(), get()) }
    }
}