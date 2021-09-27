package cz.maderajan.ui.spotifysync.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import cz.maderajan.common.ui.ErrorEffect
import cz.maderajan.common.ui.NavDirectionEffect
import cz.maderajan.common.ui.fragment.viewBinding
import cz.maderajan.common.ui.toast
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.databinding.FragmentIntroSpotifySyncBinding
import cz.maderajan.ui.spotifysync.intro.viewmodel.IntroSpotifySyncViewModel
import cz.maderajan.ui.spotifysync.intro.viewmodel.PersistSpotifyLoginToken
import cz.maderajan.ui.spotifysync.intro.viewmodel.Skip
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
                        is ErrorEffect -> toast(effect.message)
                        is NavDirectionEffect -> {
                            findNavController().navigate(effect.navDirection)
                        }
                    }
                }
        }

        binding.synchronizeButton.setOnClickListener {
            val builder: AuthenticationRequest.Builder =
                AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)

            builder.setScopes(arrayOf("user-library-read"))
            val request: AuthenticationRequest = builder.build()

            AuthenticationClient.openLoginActivity(requireActivity(), REQ_SPOTIFY_LOGIN, request)
        }

        binding.skipButton.setOnClickListener {
            viewModel.send(Skip)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_SPOTIFY_LOGIN) {
            val response = AuthenticationClient.getResponse(resultCode, data)
            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> viewModel.send(PersistSpotifyLoginToken(response.accessToken))
                AuthenticationResponse.Type.ERROR -> Timber.e(response.error)
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