package cz.maderajan.mml.data.data.mapper.artist

import cz.maderajan.mml.data.data.Artist
import cz.maderajan.mml.data.data.mapper.Mapper
import cz.maderajan.mml.database.entity.ArtistEntity

class ArtistEntityToArtistMapper : Mapper<ArtistEntity, Artist> {

    override fun map(input: ArtistEntity): Artist =
        Artist(
            id = input.artistId,
            name = input.name,
            image = input.image
        )
}