package cz.maderajan.mml.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlbumEntity(
    @PrimaryKey
    val id: String
)