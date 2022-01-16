package cz.maderajan.common.ui.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import cz.maderajan.common.resources.MmmlTypography
import cz.maderajan.common.resources.PrimaryColor600
import cz.maderajan.common.resources.TextButton
import cz.maderajan.common.ui.R

@Composable
fun Banner(
    @DrawableRes imageRes: Int,
    descriptionText: String,
    positiveButtonText: String,
    positiveButtonCallback: () -> Unit,
    modifier: Modifier = Modifier,
    negativeButtonText: String? = null,
    negativeButtonCallback: (() -> Unit)? = null,
) {
    ConstraintLayout(
        modifier = modifier.apply {
            fillMaxWidth()
        }
    ) {
        val (image, description, positiveButton, negativeButton, divider) = createRefs()

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "",
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .padding(start = 16.dp, top = 16.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = descriptionText,
            style = MmmlTypography.body1,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .constrainAs(description) {
                    width = Dimension.fillToConstraints
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        )

        TextButton(
            onClick = positiveButtonCallback,
            text = positiveButtonText,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 16.dp)
                .constrainAs(positiveButton) {
                    end.linkTo(parent.end)
                    top.linkTo(description.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )

        if (negativeButtonCallback != null && negativeButtonText != null) {
            TextButton(
                onClick = negativeButtonCallback,
                text = negativeButtonText,
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 16.dp)
                    .constrainAs(negativeButton) {
                        end.linkTo(positiveButton.start)
                        top.linkTo(description.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }

        Divider(
            color = PrimaryColor600,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(divider) {
                    top.linkTo(positiveButton.bottom)
                }
        )
    }
}

@Preview(widthDp = 350)
@Composable
fun PreviewBanner() {
    Banner(
        imageRes = R.drawable.img_spotify,
        descriptionText = "Description of the banner is often very long",
        positiveButtonText = "Positive button",
        positiveButtonCallback = {},
        negativeButtonText = "Negative button",
        negativeButtonCallback = {},
    )
}
