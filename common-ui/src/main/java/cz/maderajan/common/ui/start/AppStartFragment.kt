package cz.maderajan.common.ui.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import cz.maderajan.common.ui.NavFlowEffect
import cz.maderajan.common.ui.NavigationFlowBus
import cz.maderajan.common.ui.R
import cz.maderajan.common.ui.usecase.AppStartUseCase
import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.common.ui.viewmodel.BaseViewModelState
import cz.maderajan.common.ui.viewmodel.IAction
import cz.maderajan.navigation.NavigationFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class AppStartFragment : Fragment(R.layout.fragment_start) {

    private val viewModel: AppStartViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.uiEffect.consumeAsFlow()
                .collect { effect ->
                    when (effect) {
                        is NavFlowEffect -> {
                            viewModel.navigator.send(effect.navFlow)
                        }
                    }
                }
        }

        viewModel.send(AppStartAction.Start)
    }
}

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
                                    sendEffect(NavFlowEffect(nav))
                                }
                            }
                    }
                }
            }
    }
}

object AppStartViewState : BaseViewModelState

sealed class AppStartAction : IAction {
    object Start : AppStartAction()
}