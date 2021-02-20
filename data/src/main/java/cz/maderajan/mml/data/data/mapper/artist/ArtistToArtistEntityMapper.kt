package cz.maderajan.mml.data.data.mapper.artist

import cz.maderajan.mml.data.data.Artist
import cz.maderajan.mml.data.data.mapper.Mapper
import cz.maderajan.mml.database.entity.ArtistEntity

class ArtistToArtistEntityMapper : Mapper<Artist, ArtistEntity> {

    override fun map(input: Artist): ArtistEntity =
        ArtistEntity(
            artistId = input.id,
            name = input.name,
            image = input.image
        )
}