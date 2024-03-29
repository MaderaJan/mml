package cz.maderajan.mml.database.dao

import androidx.room.*
import cz.maderajan.mml.database.entity.AlbumArtistCrossRef
import cz.maderajan.mml.database.entity.AlbumEntity
import cz.maderajan.mml.database.entity.AlbumWithArtistEntityDto
import cz.maderajan.mml.database.entity.ArtistEntity

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbum(albumEntity: AlbumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbums(albumEntity: List<AlbumEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtist(artistEntity: ArtistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(artistEntity: List<ArtistEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbumArtistCrossRef(crossRef: List<AlbumArtistCrossRef>)

    @Transaction
    @Query("SELECT * FROM AlbumEntity")
    suspend fun getAllAlbums(): List<AlbumWithArtistEntityDto>
}