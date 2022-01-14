package cz.maderajan.common.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.maderajan.common.resources.ContainedButton
import cz.maderajan.common.resources.TextButton

@Composable
fun BottomPairButtons(
    primaryButtonText: String,
    primaryButtonCallback: () -> Unit,
    secondaryButtonText: String,
    secondaryButtonCallback: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContainedButton(
            text = primaryButtonText,
            onClick = primaryButtonCallback,
        )
        TextButton(
            onClick = secondaryButtonCallback,
            text = secondaryButtonText
        )
    }
}

@Preview
@Composable
fun PreviewBottomControlButtons() {
    BottomPairButtons(
        primaryButtonText = "Primary button",
        primaryButtonCallback = {},
        secondaryButtonText = "Secondary button",
        secondaryButtonCallback = {},
    )
}