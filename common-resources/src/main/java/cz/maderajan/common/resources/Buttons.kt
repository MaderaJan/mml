package cz.maderajan.common.resources

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContainedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = SecondaryColo900,
            contentColor = TextColorDark,
        ),
        shape = RoundedCornerShape(32.dp),
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = MmmlTypography.body1,
            color = TextColorDark,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
            )
        )
    }
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material.TextButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = MmmlTypography.body1,
            color = TextColorLight,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
            )
        )
    }
}