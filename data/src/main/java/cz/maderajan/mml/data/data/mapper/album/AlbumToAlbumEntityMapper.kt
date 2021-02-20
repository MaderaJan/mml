package cz.maderajan.mml.data.data.mapper.album

import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.data.mapper.Mapper
import cz.maderajan.mml.database.entity.AlbumEntity

class AlbumToAlbumEntityMapper : Mapper<Album, AlbumEntity> {

    override fun map(input: Album): AlbumEntity =
        AlbumEntity(
            albumId = input.id,
            name = input.name,
            image = input.image,
            genres = input.genres
        )
}