package cz.maderajan.common.ui.start

import cz.maderajan.common.ui.NavigationFlowBus
import cz.maderajan.common.ui.UiEffect
import cz.maderajan.common.ui.usecase.AppStartUseCase
import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.navigation.NavigationFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flowOf

@ExperimentalCoroutinesApi
class AppStartViewModel(
    val navigator: NavigationFlowBus,
    private val appStartUseCase: AppStartUseCase
) : BaseMviViewModel<AppStartViewState, AppStartAction>(AppStartViewState) {

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                when (action) {
                    AppStartAction.Start -> {
                        appStartUseCase.isFirstSyncComplete()
                            .catch {
                                flowOf(Unit)
                            }.collect { isFirstSyncComplete ->
                                if (isFirstSyncComplete) {
                                    NavigationFlow.Albums
                                } else {
                                    NavigationFlow.SpotifySync
                                }.also { nav ->
                                    sendEffect(UiEffect.NavFlowUiEffect(nav))
                                }
                            }
                    }
                }
            }
    }
}