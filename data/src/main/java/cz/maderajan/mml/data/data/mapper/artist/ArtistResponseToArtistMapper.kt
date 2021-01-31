package cz.maderajan.mml.data.data.mapper.artist

import cz.maderajan.mml.data.data.Artist
import cz.maderajan.mml.data.data.mapper.Mapper
import cz.maderajan.mml.webservice.response.ArtistResponse

class ArtistResponseToArtistMapper : Mapper<ArtistResponse, Artist> {

    override fun map(input: ArtistResponse): Artist =
        Artist(
            id = input.id,
            name = input.name,
            image = input.images?.firstOrNull()?.url
        )
}