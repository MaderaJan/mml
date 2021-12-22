package cz.maderajan.ui.spotifysync.intro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import cz.maderajan.common.resources.ContainedButton
import cz.maderajan.common.resources.GrayColor500
import cz.maderajan.common.resources.MmlTheme
import cz.maderajan.common.resources.TextButton
import cz.maderajan.common.ui.UiEffect
import cz.maderajan.common.ui.toast
import cz.maderajan.navigation.NavigationFlow
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.intro.viewmodel.IntroSpotifyAction
import cz.maderajan.ui.spotifysync.intro.viewmodel.IntroSpotifySyncViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class IntroSpotifySyncScreen : Fragment() {

    private val viewModel: IntroSpotifySyncViewModel by viewModel()

    @SuppressLint("Range")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                MmlTheme {
                    ConstraintLayout {
                        val (titleText, subTitleText, spotifyImage, noteImage, synchronizeButton, skipButton) = createRefs()
                        val typography = MaterialTheme.typography

                        Image(
                            painter = painterResource(id = R.drawable.ic_music),
                            colorFilter = ColorFilter.tint(
                                GrayColor500,
                            ),
                            modifier = Modifier
                                .width(72.dp)
                                .height(72.dp)
                                .constrainAs(noteImage) {
                                    bottom.linkTo(spotifyImage.bottom)
                                    start.linkTo(spotifyImage.start)
                                    top.linkTo(spotifyImage.top)
                                    linkTo(spotifyImage.top, spotifyImage.bottom, bias = 1F)
                                },
                            contentDescription = "",
                        )
                        Image(
                            painter = painterResource(id = R.drawable.img_spotify),
                            contentDescription = "",
                            Modifier
                                .width(330.dp)
                                .height(330.dp)
                                .constrainAs(spotifyImage) {
                                    linkTo(parent.start, parent.end, bias = 2F)
                                    linkTo(parent.top, parent.bottom, bias = 0.45F)
                                    bottom.linkTo(parent.bottom)
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                        Text(
                            text = stringResource(id = R.string.spotify_synchronization_title),
                            style = typography.h3,
                            modifier = Modifier.constrainAs(titleText) {
                                width = Dimension.fillToConstraints
                                start.linkTo(parent.start, margin = 24.dp)
                                end.linkTo(parent.end, margin = 24.dp)
                                top.linkTo(parent.top, margin = 8.dp)
                            }
                        )
                        Text(
                            text = stringResource(id = R.string.spotify_synchronization_subtitle),
                            style = typography.body1,
                            modifier = Modifier.constrainAs(subTitleText) {
                                width = Dimension.fillToConstraints
                                start.linkTo(parent.start, margin = 24.dp)
                                end.linkTo(parent.end, margin = 24.dp)
                                top.linkTo(titleText.bottom, margin = 8.dp)
                            }
                        )
                        ContainedButton(
                            text = stringResource(id = R.string.spotify_synchronization_synchronization),
                            onClick = ::onSynchronizeClick,
                            modifier = Modifier.constrainAs(synchronizeButton) {
                                start.linkTo(parent.start, margin = 16.dp)
                                end.linkTo(parent.end, margin = 16.dp)
                                bottom.linkTo(skipButton.top, margin = 8.dp)
                            }
                        )
                        TextButton(
                            onClick = ::onSendClick,
                            modifier = Modifier.constrainAs(skipButton) {
                                bottom.linkTo(parent.bottom, margin = 16.dp)
                                start.linkTo(parent.start, margin = 16.dp)
                                end.linkTo(parent.end, margin = 16.dp)
                            }
                        ) {
                            Text(text = stringResource(id = R.string.spotify_synchronization_skip))
                        }
                    }
                }
            }
        }

    private fun onSendClick() {
        viewModel.send(IntroSpotifyAction.Skip)
    }

    private fun onSynchronizeClick() {
        val builder: AuthorizationRequest.Builder =
            AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)

        builder.setScopes(arrayOf("user-library-read"))
        val request: AuthorizationRequest = builder.build()
        AuthorizationClient.openLoginActivity(requireActivity(), REQ_SPOTIFY_LOGIN, request)
    }

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