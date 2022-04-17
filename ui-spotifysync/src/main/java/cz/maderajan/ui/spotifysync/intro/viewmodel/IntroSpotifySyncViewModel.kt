package cz.maderajan.ui.spotifysync.intro.viewmodel

import com.spotify.sdk.android.auth.AuthorizationResponse
import cz.maderajan.common.ui.UiEffect
import cz.maderajan.common.ui.viewmodel.BaseMviViewModel
import cz.maderajan.navigation.NavigationFlowBus
import cz.maderajan.navigation.direction.AlbumsDirection
import cz.maderajan.navigation.direction.SpotifyDirection
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.usecase.IntroSpotifyUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import timber.log.Timber

class IntroSpotifySyncViewModel(
    private val navigationFlowBus: NavigationFlowBus,
    private val introSpotifyUseCase: IntroSpotifyUseCase
) : BaseMviViewModel<IntroSpotifyViewState, IntroSpotifyAction>(IntroSpotifyViewState()) {

    override suspend fun handleActions() {
        actions.consumeAsFlow()
            .collect { action ->
                when (action) {
                    is IntroSpotifyAction.SpotifyResponse -> {
                        val authResponse = action.authResponse
                        when (authResponse.type) {
                            AuthorizationResponse.Type.TOKEN -> {
                                storeToken(authResponse.accessToken)
                            }
                            AuthorizationResponse.Type.ERROR -> {
                                Timber.e(authResponse.error)
                            }
                            else -> Timber.e(authResponse.state)
                        }
                    }
                    is IntroSpotifyAction.Skip -> {
                        introSpotifyUseCase.synchronizationSkipped()
                        navigationFlowBus.send(AlbumsDirection.root)
                    }
                }
            }
    }

    private suspend fun storeToken(token: String?) {
        if (token.isNullOrEmpty()) {
            sendEffect(UiEffect.ErrorUiEffect(R.string.general_error_something_went_wrong))
        } else {
            introSpotifyUseCase.persistSpotifyAccessToken(token)
            navigationFlowBus.send(SpotifyDirection.selectAlbums)
        }
    }
}