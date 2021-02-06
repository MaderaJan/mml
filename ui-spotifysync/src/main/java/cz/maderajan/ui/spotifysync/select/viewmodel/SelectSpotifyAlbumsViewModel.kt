package cz.maderajan.ui.spotifysync.select.viewmodel

import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.mml.commonutil.LoadingEffect
import cz.maderajan.mml.commonutil.ReadyEffect
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
                        uiEffect.send(LoadingEffect)

                        val albums = syncSpotifyAlbumsUseCase.fetchAllUserAlbums()
                        setState { copy(albums = albums) }
                        sendEffect(ReadyEffect)
                    }
                    is AlbumClicked -> {
                        val selectedAlbum = action.album
                        val updatedAlbums = state.value.albums.toMutableList()
                        val index = updatedAlbums.indexOf(selectedAlbum)
                        if (index != -1) {
                            updatedAlbums[index] = selectedAlbum.copy(isSelected = !selectedAlbum.isSelected)
                        }
                        setState {
                            copy(albums = updatedAlbums)
                        }
                    }
                }
            }
    }
}