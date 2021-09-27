package cz.maderajan.mml.ui.viewmodel

import cz.maderajan.common.ui.SuccessEffect
import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.mml.R
import cz.maderajan.mml.usecase.MainUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flowOf

@ExperimentalCoroutinesApi
class MainActivityViewModel(
    private val mainUseCase: MainUseCase
) : BaseMviViewModel<MainViewModelState, MainAction>(MainViewModelState) {

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                when (action) {
                    Start -> {
                        mainUseCase.isFirstSyncComplete()
                            .catch {
                                flowOf(Unit)
                            }.collect { isFirstSyncComplete ->
                                if (isFirstSyncComplete) {
                                    R.id.navigation_albums
                                } else {
                                    R.id.navigation_spotifysync
                                }.also { nav ->
                                    sendEffect(SuccessEffect(nav))
                                }
                            }
                    }
                }
            }
    }
}