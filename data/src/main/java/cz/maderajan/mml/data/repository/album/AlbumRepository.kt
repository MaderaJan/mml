package cz.maderajan.mml.data.repository.album

import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.data.mapper.DataMapperFacade
import cz.maderajan.mml.data.data.mapper.forLists
import cz.maderajan.mml.database.dao.AlbumDao
import cz.maderajan.mml.database.entity.AlbumArtistCrossRef
import cz.maderajan.mml.database.entity.AlbumEntity
import cz.maderajan.mml.database.entity.ArtistEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion

class AlbumRepository(
    private val albumDao: AlbumDao,
    private val dataMapperFacade: DataMapperFacade,
) {

    suspend fun saveAlbums(albums: List<Album>): Flow<Unit> {
        val allAlbumEntities = mutableListOf<AlbumEntity>()
        val allArtistEntities = mutableListOf<ArtistEntity>()
        val allAlbumArtistCrossRefEntities = mutableListOf<AlbumArtistCrossRef>()

        albums.forEach { album ->
            val albumEntity = dataMapperFacade.albumToAlbumEntityMapper.map(album)
            val artistEntities = dataMapperFacade.artistToArtistEntityMapper.forLists()(album.artists)
            val albumArtistEntities = artistEntities.map { artistEntity ->
                AlbumArtistCrossRef(albumId = albumEntity.albumId, artistId = artistEntity.artistId)
            }

            allAlbumEntities.add(albumEntity)
            allArtistEntities.addAll(artistEntities)
            allAlbumArtistCrossRefEntities.addAll(albumArtistEntities)
        }

        return flowOf(
            albumDao.saveAlbums(allAlbumEntities)
        ).onCompletion {
            albumDao.saveArtists(allArtistEntities)
        }.onCompletion {
            albumDao.saveAlbumArtistCrossRef(allAlbumArtistCrossRefEntities)
        }
    }

    suspend fun getAllAlbums(): Flow<List<Album>> =
        flowOf(
            albumDao.getAllAlbums()
        ).map { albums ->
            albums.map { album ->
                dataMapperFacade.albumWithArtistWithArtistsDtoDtoToAlbumMapper.map(album)
            }
        }
}