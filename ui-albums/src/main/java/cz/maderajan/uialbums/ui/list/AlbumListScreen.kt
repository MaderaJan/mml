package cz.maderajan.uialbums.ui.list

import androidx.compose.runtime.Composable
import cz.maderajan.common.resources.MmlTheme
import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.common.ui.viewmodel.BaseViewState
import cz.maderajan.common.ui.viewmodel.IAction
import cz.maderajan.uialbums.usecase.AlbumListUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow

@Composable
fun AlbumListScreen(
    viewModel: AlbumListViewModel
) {
    MmlTheme {
        viewModel.send(AlbumListAction.GetAlbums)

        // TODO UI For albums
    }
}

class AlbumListViewModel(
    private val albumListUseCase: AlbumListUseCase
) : BaseMviViewModel<AlbumListViewState, AlbumListAction>(AlbumListViewState()) {

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                when (action) {
                    AlbumListAction.GetAlbums -> {
                        albumListUseCase.getAlbums()
                    }
                }
            }
    }
}

class AlbumListViewState : BaseViewState

sealed class AlbumListAction : IAction {
    object GetAlbums : AlbumListAction()
}