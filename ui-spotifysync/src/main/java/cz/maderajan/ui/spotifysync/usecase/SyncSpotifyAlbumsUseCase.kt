package cz.maderajan.ui.spotifysync.usecase

import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.repository.album.AlbumRepository
import cz.maderajan.mml.data.repository.auth.TokenRepository
import cz.maderajan.mml.data.repository.spotify.SpotifyRepository
import cz.maderajan.ui.spotifysync.data.select.ISelectableAlbum
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.singleOrNull

class SyncSpotifyAlbumsUseCase(
    private val spotifyRepository: SpotifyRepository,
    private val albumRepository: AlbumRepository,
    private val tokenRepository: TokenRepository
) {

    suspend fun fetchAllUserAlbums(): Flow<List<SelectableAlbum>> {
        val token = tokenRepository.getSpotifyAccessToken()

        return spotifyRepository.fetchAlbumBatch(token, offset = 0)
            .map { (firstBatchAlbums, allAlbumsCount) ->
                var allAlbums = listOf<Album>()
                allAlbums = allAlbums + firstBatchAlbums

                while (allAlbumsCount > allAlbums.size) {
                    allAlbums = spotifyRepository.fetchAlbumBatch(token, offset = allAlbums.size)
                        .map { (albumsBatch, _) ->
                            allAlbums + albumsBatch
                        }.singleOrNull() ?: emptyList()
                }

                allAlbums.sortedBy { it.name.lowercase() }
            }.map { albums ->
                albums.map { album ->
                    SelectableAlbum(
                        id = album.id,
                        name = album.name,
                        image = album.image,
                        artists = album.artists,
                        genres = album.genres,
                        isSelected = false,
                    )
                }
            }
    }

    suspend fun saveSelectedAlbums(selectableAlbums: List<ISelectableAlbum>): Flow<Unit> {
        val albums = selectableAlbums
            .filter { it is SelectableAlbum && it.isSelected }
            .map { it as Album }

        return albumRepository.saveAlbums(albums)
            .onCompletion {
                spotifyRepository.firstSyncComplete()
            }
    }
}