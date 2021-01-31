package cz.maderajan.ui.spotifysync.select.viewmodel

import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.ui.spotifysync.usecase.SyncSpotifyAlbumsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import timber.log.Timber

@ExperimentalCoroutinesApi
class SelectSpotifyAlbumsViewModel(private val syncSpotifyAlbumsUseCase: SyncSpotifyAlbumsUseCase) :
    BaseMviViewModel<SelectSpotifyAlbumsViewState, SelectSpotifyAlbumsActions>(SelectSpotifyAlbumsViewState()) {

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                when (action) {
                    SyncSpotifyAlbums -> {
                        Timber.d("START")
                        val albums = syncSpotifyAlbumsUseCase.fetchAllUserAlbums()
                        Timber.d("STOP")
                        Timber.d("albumsSize: ${albums.size}")
                    }
                }
            }
    }
}