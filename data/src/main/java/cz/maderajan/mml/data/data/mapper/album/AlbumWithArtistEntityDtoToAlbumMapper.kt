package cz.maderajan.mml.data.data.mapper.album

import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.data.mapper.Mapper
import cz.maderajan.mml.data.data.mapper.artist.ArtistEntityToArtistMapper
import cz.maderajan.mml.database.entity.AlbumWithArtistEntityDto

class AlbumWithArtistEntityDtoToAlbumMapper(private val artistEntityToArtistMapper: ArtistEntityToArtistMapper) : Mapper<AlbumWithArtistEntityDto, Album> {

    override fun map(input: AlbumWithArtistEntityDto): Album =
        Album(
            id = input.album.albumId,
            name = input.album.name,
            image = input.album.image,
            artists = input.artists.map(artistEntityToArtistMapper::map),
            genres = input.album.genres,
        )
}