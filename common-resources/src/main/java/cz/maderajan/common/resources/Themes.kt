package cz.maderajan.common.resources

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = PrimaryColor700,
    primaryVariant = PrimaryColor900,
    onPrimary = Color.White,
    secondary = PrimaryColor700,
    secondaryVariant = PrimaryColor900,
    onSecondary = Color.White,
    onBackground = Color.Black,
)

private val DarkThemeColors = darkColors(
    primary = PrimaryColor700,
    primaryVariant = PrimaryColor900,
    onPrimary = Color.White,
    secondary = PrimaryColor700,
    secondaryVariant = PrimaryColor900,
    onSecondary = Color.White,
    onBackground = Color.Black,
)

@Composable
fun MmlTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = MmmlTypography,
        content = content
    )
}