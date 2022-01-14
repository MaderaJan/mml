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
const val REQ_SPOTIFY_LOGIN = 1000

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

                builder.setScopes(arrayOf("user-library-read"))
                val request: AuthorizationRequest = builder.build()

                AuthorizationClient.openLoginActivity(context as Activity, REQ_SPOTIFY_LOGIN, request)
            },
            onSkip = {
                viewModel.send(IntroSpotifyAction.Skip)
            }
        )
    }
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        lifecycleScope.launchWhenCreated {
//            viewModel.uiEffect.consumeAsFlow()
//                .collect { effect ->
//                    when (effect) {
//                        is UiEffect.ErrorUiEffect -> toast(effect.message)
//                        is UiEffect.NavDirectionUiEffect -> {
//                            findNavController().navigate(effect.navDirection)
//                        }
//                        is UiEffect.SuccessUiEffect -> {
//                            viewModel.navigationFlowBus.send(NavigationCommand.Albums)
//                        }
//                    }
//                }
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == REQ_SPOTIFY_LOGIN) {
//            val response = AuthorizationClient.getResponse(resultCode, data)
//            when (response.type) {
//                AuthorizationResponse.Type.TOKEN -> viewModel.send(IntroSpotifyAction.PersistSpotifyLoginToken(response.accessToken))
//                AuthorizationResponse.Type.ERROR -> Timber.e(response.error)
//                else -> Timber.e(response.state)
//            }
//        }
//    }
}