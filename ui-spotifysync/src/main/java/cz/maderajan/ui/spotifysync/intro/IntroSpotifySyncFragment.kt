package cz.maderajan.ui.spotifysync.intro

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import cz.maderajan.common.resources.MmlTheme
import cz.maderajan.ui.spotifysync.intro.viewmodel.IntroSpotifyAction
import cz.maderajan.ui.spotifysync.intro.viewmodel.IntroSpotifySyncViewModel

const val CLIENT_ID = "f1c93f2c3bd64faf9c410970d69a1fac"
const val REDIRECT_URI = "mml://callback"

@Composable
fun SpotifyIntroScreen(
    viewModel: IntroSpotifySyncViewModel,
) {

    MmlTheme {
        val context = LocalContext.current

        IntroSyncUi(
            onSynchronize = {
                val builder: AuthorizationRequest.Builder =
                    AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
                        .setScopes(arrayOf("user-library-read"))

                val request: AuthorizationRequest = builder.build()
                AuthorizationClient.openLoginInBrowser(context as Activity, request)
            },
            onSkip = {
                viewModel.send(IntroSpotifyAction.Skip)
            }
        )
    }
}