package cz.maderajan.ui.spotifysync.filtr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cz.maderajan.common.resources.*
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.select.SelectSpotifyList
import cz.maderajan.ui.spotifysync.select.viewmodel.SelectSpotifyAlbumsActions
import cz.maderajan.ui.spotifysync.select.viewmodel.SelectSpotifyAlbumsViewModel

@Composable
fun SelectSpotifyFilterScreen(
    navController: NavController,
    viewModel: SelectSpotifyAlbumsViewModel
) {
    MmlTheme {
        val currentState = viewModel.state.collectAsState().value

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                FilterField(
                    filterValue = currentState.filterValue,
                    onBackButtonClicked = {
                        navController.navigateUp()
                    },
                    onFilterValueChange = { filterValue ->
                        viewModel.send(SelectSpotifyAlbumsActions.FilterValueChanged(filterValue))
                    }
                )
            }

            SelectSpotifyList(
                items = currentState.albums,
                onAlbumSelect = { album ->
                    viewModel.send(SelectSpotifyAlbumsActions.AlbumClicked(album))
                }
            )
        }
    }
}

@Composable
fun FilterField(
    filterValue: String,
    onBackButtonClicked: () -> Unit,
    onFilterValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = filterValue,
        leadingIcon = {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_arrow_back_ios
                ),
                colorFilter = ColorFilter.tint(color = PrimaryColor800),
                contentDescription = "",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onBackButtonClicked.invoke()
                    }
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.spotify_filter_placeholder),
                style = MmmlTypography.body2.copy(
                    color = TextColorLight.copy(
                        alpha = 0.7f
                    ),
                )
            )
        },
        onValueChange = onFilterValueChange,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .height(54.dp)
            .fillMaxWidth()
            .background(
                color = PrimaryColor600,
                shape = RoundedCornerShape(8.dp)
            ),
    )
}