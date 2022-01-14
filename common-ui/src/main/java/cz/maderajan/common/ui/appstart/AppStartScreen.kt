package cz.maderajan.common.ui.appstart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.maderajan.common.resources.MmlTheme
import cz.maderajan.common.resources.SecondaryColo900

@Composable
fun AppStartScreen(
    viewModel: AppStartViewModel
) {
    MmlTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = SecondaryColo900
            )
        }
    }
}