package cz.maderajan.mml.data.data.mapper.album

import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.data.mapper.Mapper
import cz.maderajan.mml.data.data.mapper.artist.ArtistEntityToArtistMapper
import cz.maderajan.mml.database.entity.AlbumEntity

class AlbumEntityToAlbumMapper(private val artistEntityToArtistMapper: ArtistEntityToArtistMapper) : Mapper<AlbumEntity, Album> {

    override fun map(input: AlbumEntity): Album =
        Album(
            id = input.albumId,
            name = input.name,
            image = input.image,
            artists = emptyList(), // Jak jsem passnout ty album, pokud mám jen AlbumEntity??
            genres = input.genres,
        )
}