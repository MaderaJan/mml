package cz.maderajan.ui.spotifysync.intro

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import cz.maderajan.common.resources.GrayColor500
import cz.maderajan.common.ui.view.BottomPairButtons
import cz.maderajan.ui.spotifysync.R

@SuppressLint("Range")
@Composable
fun IntroSyncUi(
    onSynchronize: () -> Unit,
    onSkip: () -> Unit,
) {
    ConstraintLayout {
        val (titleText, subTitleText, spotifyImage, noteImage, bottomButtons) = createRefs()
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
        BottomPairButtons(
            modifier = Modifier.constrainAs(bottomButtons) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            },
            primaryButtonText = stringResource(id = R.string.spotify_synchronization_synchronization),
            primaryButtonCallback = onSynchronize,
            secondaryButtonText = stringResource(id = R.string.spotify_synchronization_skip),
            secondaryButtonCallback = onSkip
        )
    }
}

@Preview
@Composable
fun PreviewIntroSyncUi() {
    IntroSyncUi(onSynchronize = {}, onSkip = {})
}