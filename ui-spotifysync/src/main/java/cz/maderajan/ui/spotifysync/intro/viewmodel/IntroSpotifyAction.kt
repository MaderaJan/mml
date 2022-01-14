package cz.maderajan.ui.spotifysync.intro.viewmodel

import com.spotify.sdk.android.auth.AuthorizationResponse
import cz.maderajan.common.ui.viewmodel.IAction

sealed class IntroSpotifyAction : IAction {
    class SpotifyResponse(val authResponse: AuthorizationResponse) : IntroSpotifyAction()
    object Skip : IntroSpotifyAction()
}