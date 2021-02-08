package cz.maderajan.ui.spotifysync.usecase

import cz.maderajan.mml.data.TokenRepository
import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.spotify.SpotifyRepository
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum
import java.util.*

class SyncSpotifyAlbumsUseCase(
    private val spotifyRepository: SpotifyRepository,
    private val tokenRepository: TokenRepository
) {

    suspend fun fetchAllUserAlbums(): List<SelectableAlbum> {
        val token = tokenRepository.getSpotifyAccessToken()
        val (firstBatchAlbums, allAlbumsCount) = spotifyRepository.fetchAlbumBatch(token, offset = 0)

        var allAlbums = listOf<Album>()
        allAlbums = allAlbums + firstBatchAlbums

        // TODO fetch only first batch
//        while (allAlbumsCount > allAlbums.size) {
//            val (albumsBatch, _) = spotifyRepository.fetchAlbumBatch(token, offset = allAlbums.size)
//            allAlbums = allAlbums + albumsBatch
//        }

        val selectableAlbums = allAlbums.map {
            SelectableAlbum(it.id, it.name, it.image, it.artists, it.genres, false)
        }

        return selectableAlbums.sortedBy { it.name.toLowerCase(Locale.getDefault()) }
    }
}