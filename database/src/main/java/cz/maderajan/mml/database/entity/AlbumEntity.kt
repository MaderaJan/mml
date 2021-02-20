package cz.maderajan.mml.database.entity

import androidx.room.*

@Entity
data class AlbumEntity(
    @PrimaryKey
    val albumId: String,
    val name: String,
    val image: String?,
    val genres: List<String>,
)

@Entity(primaryKeys = ["albumId", "artistId"])
data class AlbumArtistCrossRef(
    val albumId: String,
    val artistId: String
)

data class AlbumWithArtistEntityDto(
    @Embedded val album: AlbumEntity,
    @Relation(
        parentColumn = "albumId",
        entityColumn = "artistId",
        associateBy = Junction(AlbumArtistCrossRef::class)
    )
    val artists: List<ArtistEntity>
)