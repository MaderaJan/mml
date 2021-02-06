package cz.maderajan.ui.spotifysync.select.viewmodel

import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.ui.spotifysync.usecase.SyncSpotifyAlbumsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow

@ExperimentalCoroutinesApi
class SelectSpotifyAlbumsViewModel(private val syncSpotifyAlbumsUseCase: SyncSpotifyAlbumsUseCase) :
    BaseMviViewModel<SelectSpotifyAlbumsViewState, SelectSpotifyAlbumsActions>(SelectSpotifyAlbumsViewState(emptyList())) {

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                when (action) {
                    SyncSpotifyAlbums -> {
                        val albums = syncSpotifyAlbumsUseCase.fetchAllUserAlbums()
                        setState {
                            copy(albums = albums)
                        }
                    }
                }
            }
    }
}