package cz.maderajan.mml.data.data.mapper

import cz.maderajan.mml.data.data.mapper.album.AlbumResponseToAlbumMapper
import cz.maderajan.mml.data.data.mapper.album.AlbumToAlbumEntityMapper
import cz.maderajan.mml.data.data.mapper.album.AlbumWithArtistEntityDtoToAlbumMapper
import cz.maderajan.mml.data.data.mapper.artist.ArtistEntityToArtistMapper
import cz.maderajan.mml.data.data.mapper.artist.ArtistResponseToArtistMapper
import cz.maderajan.mml.data.data.mapper.artist.ArtistToArtistEntityMapper

class DataMapperFacade(
    // ALBUM
    val albumResponseToAlbumMapper: AlbumResponseToAlbumMapper,
    val albumToAlbumEntityMapper: AlbumToAlbumEntityMapper,
    val albumWithArtistWithArtistsDtoDtoToAlbumMapper: AlbumWithArtistEntityDtoToAlbumMapper,
    // ARTIST
    val artistResponseToArtistMapper: ArtistResponseToArtistMapper,
    val artistToArtistEntityMapper: ArtistToArtistEntityMapper,
    val artistEntityToArtistMapper: ArtistEntityToArtistMapper,
)