package cz.maderajan.common.ui.appstart

import cz.maderajan.common.ui.usecase.AppStartUseCase
import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.navigation.NavigationFlowBus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow

class AppStartViewModel(
    val navigator: NavigationFlowBus,
    private val appStartUseCase: AppStartUseCase
) : BaseMviViewModel<AppStartViewState, AppStartAction>(AppStartViewState) {

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                when (action) {
                    AppStartAction.Start -> {
                        // TODO
//                        appStartUseCase.isFirstSyncComplete()
//                            .catch {
//                                flowOf(Unit)
//                            }.collect { isFirstSyncComplete ->
//                                if (isFirstSyncComplete) {
//                                    NavigationCommand.Albums
//                                } else {
//                                    NavigationCommand.SpotifySync
//                                }.also { nav ->
//                                    sendEffect(UiEffect.NavFlowUiEffect(nav))
//                                }
//                            }
                    }
                }
            }
    }
}