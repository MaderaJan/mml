package cz.maderajan.ui.spotifysync.intro.viewmodel

import cz.maderajan.common.ui.NavigationFlowBus
import cz.maderajan.common.ui.UiEffect
import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.intro.IntroSpotifySyncFragmentDirections
import cz.maderajan.ui.spotifysync.usecase.IntroSpotifyUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow

@ExperimentalCoroutinesApi
class IntroSpotifySyncViewModel(
    val navigationFlowBus: NavigationFlowBus,
    private val introSpotifyUseCase: IntroSpotifyUseCase
) : BaseMviViewModel<IntroSpotifyViewState, IntroSpotifyAction>(IntroSpotifyViewState()) {

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                when (action) {
                    is IntroSpotifyAction.PersistSpotifyLoginToken -> {
                        storeToken(action)
                    }
                    is IntroSpotifyAction.Skip -> {
                        introSpotifyUseCase.synchronizationSkipped()
                        sendEffect(UiEffect.SuccessUiEffect.empty())
                    }
                }
            }
    }

    private suspend fun storeToken(action: IntroSpotifyAction.PersistSpotifyLoginToken) {
        if (action.token.isNullOrEmpty()) {
            sendEffect(UiEffect.ErrorUiEffect(R.string.general_error_something_went_wrong))
        } else {
            introSpotifyUseCase.persistSpotifyAccessToken(action.token)
            sendEffect(UiEffect.NavDirectionUiEffect(IntroSpotifySyncFragmentDirections.actionIntroSpotifySyncFragmentToSelectSpotifyAlbumsFragment()))
        }
    }
}