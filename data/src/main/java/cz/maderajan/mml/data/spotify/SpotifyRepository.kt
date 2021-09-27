package cz.maderajan.mml.data.spotify

import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.data.exceptions.apiCall
import cz.maderajan.mml.data.data.exceptions.mapSuccess
import cz.maderajan.mml.data.data.mapper.DataMapperFacade
import cz.maderajan.mml.data.data.mapper.forLists
import cz.maderajan.mml.data.datastore.PreferencesDataStore
import cz.maderajan.mml.database.dao.AlbumDao
import cz.maderajan.mml.database.entity.AlbumArtistCrossRef
import cz.maderajan.mml.database.entity.AlbumEntity
import cz.maderajan.mml.database.entity.ArtistEntity
import cz.maderajan.mml.webservice.SpotifyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion

class SpotifyRepository(
    private val spotifyApi: SpotifyApi,
    private val dataMapperFacade: DataMapperFacade,
    private val albumDao: AlbumDao,
    private val preferencesDataStore: PreferencesDataStore
) {

    suspend fun fetchAlbumBatch(token: String, offset: Int, limit: Int = 50): Flow<Pair<List<Album>, Int>> =
        flowOf(
            apiCall {
                spotifyApi.getAlbums(
                    token = token,
                    offset = offset,
                    limit = limit
                )
            }
        ).mapSuccess { result ->
            dataMapperFacade.albumResponseToAlbumMapper.forLists()(result.items) to result.total
        }

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

    suspend fun firstSyncComplete() {
        preferencesDataStore.setFirstSyncComplete()
    }
}