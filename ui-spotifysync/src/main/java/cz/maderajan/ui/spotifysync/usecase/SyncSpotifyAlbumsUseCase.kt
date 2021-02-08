package cz.maderajan.ui.spotifysync.usecase

import cz.maderajan.mml.data.TokenRepository
import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.spotify.SpotifyRepository
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class SyncSpotifyAlbumsUseCase(
    private val spotifyRepository: SpotifyRepository,
    private val tokenRepository: TokenRepository
) {

    suspend fun fetchAllUserAlbums(): Flow<List<SelectableAlbum>> {
        val token = tokenRepository.getSpotifyAccessToken()

        return spotifyRepository.fetchAlbumBatch(token, offset = 0)
            .map { (firstBatchAlbums, allAlbumsCount) ->
                var allAlbums = listOf<Album>()
                allAlbums = allAlbums + firstBatchAlbums

                // TODO fetch only first batch
//                while (allAlbumsCount > allAlbums.size) {
//                    allAlbums = spotifyRepository.fetchAlbumBatch(token, offset = allAlbums.size)
//                        .map { (albumsBatch, _) ->
//                             allAlbums + albumsBatch
//                        }.singleOrNull() ?: emptyList()
//                }

                allAlbums.sortedBy { it.name.toLowerCase(Locale.getDefault()) }
            }.map { albums ->
                albums.map {
                    SelectableAlbum(it.id, it.name, it.image, it.artists, it.genres, false)
                }
            }
    }
}