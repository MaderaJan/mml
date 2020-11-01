package cz.maderajan.mml.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.maderajan.mml.database.entity.AlbumEntity

@Database(
    entities = [AlbumEntity::class],
    version = 1,
    exportSchema = true
)
abstract class MmlDatabase : RoomDatabase() {

    companion object {
        private const val name = "mml-db"

        fun create(context: Context) =
            Room.databaseBuilder(context, MmlDatabase::class.java, name)
                .build()
    }
}