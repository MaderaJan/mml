package cz.maderajan.mml.data.data.mapper.album

import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.data.mapper.Mapper
import cz.maderajan.mml.data.data.mapper.artist.ArtistResponseToArtistMapper
import cz.maderajan.mml.webservice.response.AlbumWrapperResponse

class AlbumResponseToAlbumMapper(private val artistMapper: ArtistResponseToArtistMapper) : Mapper<AlbumWrapperResponse, Album> {

    override fun map(input: AlbumWrapperResponse): Album =
        Album(
            id = input.album.id,
            name = input.album.name,
            image = input.album.images?.firstOrNull()?.url,
            artists = input.album.artists?.map(artistMapper::map) ?: emptyList(),
            genres = input.album.genres,
        )
}