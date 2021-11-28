package cz.maderajan.ui.spotifysync.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import cz.maderajan.common.ui.UiEffect
import cz.maderajan.common.ui.fragment.viewBinding
import cz.maderajan.common.ui.toast
import cz.maderajan.navigation.NavigationFlow
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.databinding.FragmentIntroSpotifySyncBinding
import cz.maderajan.ui.spotifysync.intro.viewmodel.IntroSpotifyAction
import cz.maderajan.ui.spotifysync.intro.viewmodel.IntroSpotifySyncViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

@ExperimentalCoroutinesApi
class IntroSpotifySyncFragment : Fragment(R.layout.fragment_intro_spotify_sync) {

    private val binding by viewBinding(FragmentIntroSpotifySyncBinding::bind)
    private val viewModel: IntroSpotifySyncViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.uiEffect.consumeAsFlow()
                .collect { effect ->
                    when (effect) {
                        is UiEffect.ErrorUiEffect -> toast(effect.message)
                        is UiEffect.NavDirectionUiEffect -> {
                            findNavController().navigate(effect.navDirection)
                        }
                        is UiEffect.SuccessUiEffect -> {
                            viewModel.navigationFlowBus.send(NavigationFlow.Albums)
                        }
                    }
                }
        }

        binding.synchronizeButton.setOnClickListener {
            val builder: AuthorizationRequest.Builder =
                AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)

            builder.setScopes(arrayOf("user-library-read"))
            val request: AuthorizationRequest = builder.build()
            AuthorizationClient.openLoginActivity(requireActivity(), REQ_SPOTIFY_LOGIN, request)
        }

        binding.skipButton.setOnClickListener {
            viewModel.send(IntroSpotifyAction.Skip)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_SPOTIFY_LOGIN) {
            val response = AuthorizationClient.getResponse(resultCode, data)
            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> viewModel.send(IntroSpotifyAction.PersistSpotifyLoginToken(response.accessToken))
                AuthorizationResponse.Type.ERROR -> Timber.e(response.error)
                else -> Timber.e(response.state)
            }
        }
    }

    companion object {
        private const val CLIENT_ID = "f1c93f2c3bd64faf9c410970d69a1fac"
        private const val REDIRECT_URI = "mml://callback"
        private const val REQ_SPOTIFY_LOGIN = 1000
    }
}