package cz.maderajan.mml.data.repository.spotify

import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.data.exceptions.apiCall
import cz.maderajan.mml.data.data.exceptions.mapSuccess
import cz.maderajan.mml.data.data.mapper.DataMapperFacade
import cz.maderajan.mml.data.data.mapper.forLists
import cz.maderajan.mml.data.datastore.PreferencesDataStore
import cz.maderajan.mml.webservice.SpotifyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SpotifyRepository(
    private val spotifyApi: SpotifyApi,
    private val dataMapperFacade: DataMapperFacade,
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

    suspend fun firstSyncComplete() {
        preferencesDataStore.setFirstSyncComplete()
    }
}