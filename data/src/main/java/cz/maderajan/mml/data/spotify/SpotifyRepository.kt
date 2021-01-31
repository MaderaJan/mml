package cz.maderajan.mml.data.spotify

import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.data.mapper.album.AlbumResponseToAlbumMapper
import cz.maderajan.mml.data.data.mapper.forLists
import cz.maderajan.mml.data.datastore.PreferencesDataStore
import cz.maderajan.mml.webservice.SpotifyApi

class SpotifyRepository(
    private val preferencesDataStore: PreferencesDataStore,
    private val spotifyApi: SpotifyApi,
    private val albumMapper: AlbumResponseToAlbumMapper
) {

    suspend fun persistAccessToken(token: String) {
        preferencesDataStore.storeSpotifyAccessToken(token)
    }

    suspend fun fetchAlbumBatch(token: String, offset: Int, limit: Int = 50): Pair<List<Album>, Int> {
        val albums = spotifyApi.getAlbums(
            token = token,
            offset = offset,
            limit = limit
        ).await()


        return albumMapper.forLists()(albums.items) to albums.total
    }
}