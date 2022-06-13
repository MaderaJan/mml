package cz.maderajan.common.ui.appstart

import cz.maderajan.common.ui.usecase.AppStartUseCase
import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.navigation.NavigationFlowBus
import cz.maderajan.navigation.direction.AlbumsDirection
import cz.maderajan.navigation.direction.SpotifyDirection
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

class AppStartViewModel(
    private val navigator: NavigationFlowBus,
    private val appStartUseCase: AppStartUseCase
) : BaseMviViewModel<AppStartViewState, AppStartAction>(AppStartViewState) {

    init {
        send(AppStartAction.Start)
    }

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                Timber.d(action.toString())

                when (action) {
                    AppStartAction.Start -> {
                        appStartUseCase.isFirstSyncComplete()
                            .catch {
                                flowOf(Unit)
                            }.collect { isFirstSyncComplete ->
                                Timber.d("SSDSDCSC")
                                val navCommand = if (isFirstSyncComplete) {
                                    AlbumsDirection.root
                                } else {
                                    SpotifyDirection.root
                                }
                                navigator.send(navCommand)
                            }
                    }
                }
            }
    }
}