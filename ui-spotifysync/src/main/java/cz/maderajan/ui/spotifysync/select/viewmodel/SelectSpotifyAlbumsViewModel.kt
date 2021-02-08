package cz.maderajan.ui.spotifysync.select.viewmodel

import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.mml.commonutil.ErrorEffect
import cz.maderajan.mml.commonutil.LoadingEffect
import cz.maderajan.mml.commonutil.ReadyEffect
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.data.select.AlphabetLetter
import cz.maderajan.ui.spotifysync.data.select.ISelectableAlbum
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum
import cz.maderajan.ui.spotifysync.usecase.SyncSpotifyAlbumsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class SelectSpotifyAlbumsViewModel(private val syncSpotifyAlbumsUseCase: SyncSpotifyAlbumsUseCase) :
    BaseMviViewModel<SelectSpotifyAlbumsViewState, SelectSpotifyAlbumsActions>(SelectSpotifyAlbumsViewState(emptyList())) {

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                when (action) {
                    SyncSpotifyAlbums -> {
                        uiEffect.send(LoadingEffect)

                        syncSpotifyAlbumsUseCase.fetchAllUserAlbums()
                            .flowOn(Dispatchers.IO)
                            .map(::decorateAlbumsWithAlphaLetter)
                            .catch {
                                flowOf(emptyList<SelectableAlbum>())
                                sendEffect(ErrorEffect(R.string.general_error))
                            }
                            .collect { decoratedAlbums ->
                                setState { copy(albums = decoratedAlbums) }
                                sendEffect(ReadyEffect)
                            }
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
                    SelectAllAlbums -> {
                        val updatedAlbums = state.value.albums.map {
                            if (it is SelectableAlbum) it.copy(isSelected = true) else it
                        }
                        setState { copy(albums = updatedAlbums) }
                    }
                }
            }
    }

    private fun decorateAlbumsWithAlphaLetter(albums: List<ISelectableAlbum>): List<ISelectableAlbum> {
        val mutableList = albums.toMutableList()
        val iterator = mutableList.listIterator()

        var lastStartLetter: Char? = null

        while (iterator.hasNext()) {
            val current = iterator.next()
            if (current is SelectableAlbum) {
                val startLetter = current.name.first()

                if (lastStartLetter != startLetter) {
                    iterator.previous()
                    iterator.add(AlphabetLetter(startLetter))
                    iterator.next()

                    lastStartLetter = startLetter
                }
            }
        }

        return mutableList
    }
}