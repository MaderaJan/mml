package cz.maderajan.ui.spotifysync.select.viewmodel

import androidx.lifecycle.viewModelScope
import cz.maderajan.common.ui.UiEffect
import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.navigation.NavigationFlowBus
import cz.maderajan.navigation.direction.AlbumsDirection
import cz.maderajan.navigation.direction.SpotifyDirection
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.data.select.AlphabetLetter
import cz.maderajan.ui.spotifysync.data.select.ISelectableAlbum
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum
import cz.maderajan.ui.spotifysync.usecase.SyncSpotifyAlbumsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SelectSpotifyAlbumsViewModel(
    private val navigationFlowBus: NavigationFlowBus,
    private val syncSpotifyAlbumsUseCase: SyncSpotifyAlbumsUseCase
) : BaseMviViewModel<SelectSpotifyAlbumsViewState, SelectSpotifyAlbumsActions>(SelectSpotifyAlbumsViewState(emptyList())) {

    private var allAlbums = listOf<SelectableAlbum>()

    init {
        send(SelectSpotifyAlbumsActions.FetchSpotifyAlbums)
    }

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                when (action) {
                    SelectSpotifyAlbumsActions.FetchSpotifyAlbums -> {
                        uiEffect.send(UiEffect.LoadingUiEffect)

                        syncSpotifyAlbumsUseCase.fetchAllUserAlbums()
                            .flowOn(Dispatchers.IO)
                            .catch {
                                flowOf(emptyList<SelectableAlbum>())
                                sendEffect(UiEffect.ErrorUiEffect(R.string.general_error))
                            }
                            .collect { albums ->
                                allAlbums = albums
                                val decoratedAlbums = albums.decorateAlbumsWithAlphaLetter()
                                setState { copy(albums = decoratedAlbums) }
                                sendEffect(UiEffect.ReadyUiEffect)
                            }
                    }
                    is SelectSpotifyAlbumsActions.AlbumClicked -> {
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
                    SelectSpotifyAlbumsActions.SelectAllAlbums -> {
                        val updatedAlbums = state.value.albums.map {
                            if (it is SelectableAlbum) it.copy(isSelected = true) else it
                        }
                        setState { copy(albums = updatedAlbums, showBanner = false) }
                    }
                    SelectSpotifyAlbumsActions.SaveSelectedAlbums -> {
                        syncSpotifyAlbumsUseCase.saveSelectedAlbums(state.value.albums)
                            .flowOn(Dispatchers.IO)
                            .catch {
                                flowOf(Unit)
                                sendEffect(UiEffect.ErrorUiEffect(R.string.general_error))
                            }
                            .collect {
                                navigationFlowBus.send(AlbumsDirection.root)
                            }
                    }
                    SelectSpotifyAlbumsActions.HideBanner -> {
                        setState { copy(showBanner = false) }
                    }
                    SelectSpotifyAlbumsActions.OpenFilterAction -> {
                        navigationFlowBus.send(SpotifyDirection.filter)
                    }
                    is SelectSpotifyAlbumsActions.FilterValueChanged -> {
                        filterValueChanged(action.value)
                    }
                }
            }
    }

    private fun List<ISelectableAlbum>.decorateAlbumsWithAlphaLetter(): List<ISelectableAlbum> {
        val mutableList = this.toMutableList()
        val iterator = mutableList.listIterator()

        var lastStartLetter: Char? = null

        while (iterator.hasNext()) {
            val current = iterator.next()
            if (current is SelectableAlbum) {
                val startLetter = current.name.first().uppercaseChar()

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

    private fun filterValueChanged(filterValue: String) {
        var debounceJob: Job? = null

        debounceJob = viewModelScope.launch {
            debounceJob?.cancel()
            delay(500)

            val selectedAlbumIds = state.value.albums.filter { album ->
                album is SelectableAlbum && album.isSelected
            }.mapNotNull {
                if (it is SelectableAlbum) it.id else null
            }

            val filterAlbums = allAlbums.filter {
                it.name.lowercase().startsWith(filterValue.lowercase())
            }.map { album ->
                album.copy(isSelected = selectedAlbumIds.any { it == album.id })
            }

            setState {
                copy(
                    filterValue = filterValue,
                    albums = filterAlbums.decorateAlbumsWithAlphaLetter()
                )
            }
        }
    }
}