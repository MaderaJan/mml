package cz.maderajan.ui.spotifysync.usecase

import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.datastore.PreferencesDataStore
import cz.maderajan.mml.data.spotify.SpotifyRepository

class SyncSpotifyAlbumsUseCase(
    private val spotifyRepository: SpotifyRepository,
    private val preferencesDataStore: PreferencesDataStore
) {

    suspend fun fetchAllUserAlbums(): List<Album> {
        val token = preferencesDataStore.getSpotifyBearerAccessToken()
        val (firstBatchAlbums, allAlbumsCount) = spotifyRepository.fetchAlbumBatch(token, offset = 0)

        var allAlbums = listOf<Album>()
        allAlbums = allAlbums + firstBatchAlbums

        // TODO fetch only first batch
//        while (allAlbumsCount > allAlbums.size) {
//            val (albumsBatch, _) = spotifyRepository.fetchAlbumBatch(token, offset = allAlbums.size)
//            allAlbums = allAlbums + albumsBatch
//        }

        return allAlbums
    }
}