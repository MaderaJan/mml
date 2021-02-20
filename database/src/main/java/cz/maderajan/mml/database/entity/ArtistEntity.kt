package cz.maderajan.mml.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArtistEntity(
    @PrimaryKey
    val artistId: String,
    val name: String,
    val image: String?
)