package cz.maderajan.ui.spotifysync.intro.viewmodel

import androidx.lifecycle.viewModelScope
import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.mml.commonutil.ErrorEffect
import cz.maderajan.mml.commonutil.SuccessEffect
import cz.maderajan.ui.spotifysync.usecase.IntroSpotifyUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class IntroSpotifySyncViewModel(private val introSpotifyUseCase: IntroSpotifyUseCase) :
    BaseMviViewModel<IntroSpotifyViewState, IntroSpotifyAction>(IntroSpotifyViewState()) {

    // TODO -> dočasné řešení
    init {
        viewModelScope.launch {
            if (introSpotifyUseCase.getSpotifyAccessToken()) {
                sendEffect(SuccessEffect())
            }
        }
    }

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                when (action) {
                    is PersistSpotifyLoginToken -> {
                        storeToken(action)
                    }
                }
            }
    }

    private suspend fun storeToken(action: PersistSpotifyLoginToken) {
        if (action.token.isNullOrEmpty()) {
            sendEffect(ErrorEffect())
        } else {
            introSpotifyUseCase.persistSpotifyAccessToken(action.token)
            sendEffect(SuccessEffect())
        }
    }
}