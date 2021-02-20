package cz.maderajan.mml.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.maderajan.mml.database.converters.ListStringConverter
import cz.maderajan.mml.database.dao.AlbumDao
import cz.maderajan.mml.database.entity.AlbumArtistCrossRef
import cz.maderajan.mml.database.entity.AlbumEntity
import cz.maderajan.mml.database.entity.ArtistEntity

@Database(
    entities = [
        AlbumEntity::class,
        ArtistEntity::class,
        AlbumArtistCrossRef::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(ListStringConverter::class)
abstract class MmlDatabase : RoomDatabase() {

    companion object {
        private const val name = "mml-db"

        fun create(context: Context) =
            Room.databaseBuilder(context, MmlDatabase::class.java, name)
                .build()
    }

    abstract fun albumDao(): AlbumDao
}