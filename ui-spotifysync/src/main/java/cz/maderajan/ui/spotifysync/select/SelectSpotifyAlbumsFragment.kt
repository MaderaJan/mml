package cz.maderajan.ui.spotifysync.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import cz.maderajan.common.resources.*
import cz.maderajan.common.ui.UiEffect
import cz.maderajan.mml.data.data.Artist
import cz.maderajan.navigation.NavigationFlow
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.data.select.AlphabetLetter
import cz.maderajan.ui.spotifysync.data.select.ISelectableAlbum
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum
import cz.maderajan.ui.spotifysync.select.viewmodel.SelectSpotifyAlbumsActions
import cz.maderajan.ui.spotifysync.select.viewmodel.SelectSpotifyAlbumsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

@Preview
@Composable
fun PreviewSelectSpotifyTopBar() {
    SelectSpotifyTopBar {
    }
}

@Composable
fun SelectSpotifyTopBar(
    onSaveClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.spotify_select_albums_toolbar_title))
        },
        actions = {
            Text(
                text = stringResource(id = R.string.general_save),
                color = SecondaryColo900,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        onSaveClick.invoke()
                    }
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun SelectSpotifyList(
    items: List<ISelectableAlbum>,
    onAlbumSelect: (SelectableAlbum) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(items) { item ->
            when (item) {
                is SelectableAlbum -> SelectSpotifyListItem(item, onAlbumSelect)
                is AlphabetLetter -> AlphabetLetterListItem(item)
            }
        }
    }
}

@Preview
@Composable
fun PreviewSelectSpotifyListItem() {
    SelectSpotifyListItem(
        album = SelectableAlbum(
            id = "",
            name = "Album name",
            image = null,
            artists = listOf(Artist("", "Artist Name", null)),
            genres = emptyList(),
            isSelected = true,
        ),
        onAlbumSelect = {}
    )
}

@Composable
fun SelectSpotifyListItem(album: SelectableAlbum, onAlbumSelect: (SelectableAlbum) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .clickable {
                onAlbumSelect.invoke(album)
            }
            .background(color = PrimaryColor900)
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        val (title, subtitle, coverImage, selectImage) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = "",
            modifier = Modifier
                .background(shape = CircleShape, color = if (album.isSelected) GreenColor700 else Color.White)
                .padding(4.dp)
                .width(32.dp)
                .height(32.dp)
                .constrainAs(selectImage) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Image(
            painter = rememberImagePainter(
                data = album.image,
                builder = {
                    placeholder(R.drawable.ic_album_placehoder)
                    error(R.drawable.img_spotify)
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = "",
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .constrainAs(coverImage) {
                    start.linkTo(selectImage.end, margin = 16.dp)
                }
        )
        Text(
            text = album.name,
            style = MmmlTypography.subtitle2,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.constrainAs(title) {
                width = Dimension.fillToConstraints
                start.linkTo(coverImage.end, margin = 8.dp)
                end.linkTo(parent.end, margin = 16.dp)
                top.linkTo(coverImage.top)
            }
        )
        Text(
            text = album.presentableArtist,
            style = MmmlTypography.body2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(subtitle) {
                width = Dimension.fillToConstraints
                start.linkTo(coverImage.end, margin = 8.dp)
                end.linkTo(parent.end, margin = 16.dp)
                bottom.linkTo(coverImage.bottom)
            }
        )
    }
}

@Composable
fun AlphabetLetterListItem(item: AlphabetLetter) {
    Text(
        text = item.letter.toString(),
        style = MmmlTypography.subtitle1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, top = 4.dp, bottom = 4.dp)
    )
}

@Preview
@Composable
fun PreviewAlphabetLetterListItem() {
    AlphabetLetterListItem(AlphabetLetter('A'))
}

class SelectSpotifyAlbumsFragment : Fragment(R.layout.fragment_select_spotify_albums) {

    private val viewModel: SelectSpotifyAlbumsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                MmlTheme {
                    Column {
                        SelectSpotifyTopBar {
                            viewModel.send(SelectSpotifyAlbumsActions.SaveSelectedAlbums)
                        }

                        val currentState = viewModel.state.collectAsState().value
                        if (currentState.albums.isEmpty()) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator(
                                    color = SecondaryColo900
                                )
                            }
                        } else {
                            SelectSpotifyList(
                                items = currentState.albums,
                                onAlbumSelect = { album ->
                                    viewModel.send(SelectSpotifyAlbumsActions.AlbumClicked(album))
                                },
                            )
                        }
                    }
                }
            }
        }

//    private fun onAlbumSelect(album: SelectableAlbum) {
//        viewModel.send(SelectSpotifyAlbumsActions.AlbumClicked(album))
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        setHasOptionsMenu(true)
//        binding.topAppBar.inflateMenu(R.menu.menu_done)
//        binding.topAppBar.setOnMenuItemClickListener { item ->
//            when (item.itemId) {
//                R.id.action_done -> {
//                    viewModel.send(SelectSpotifyAlbumsActions.SaveSelectedAlbums)
//                    true
//                }
//                else -> false
//            }
//        }
//
//        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val adapter = SelectableAlbumAdapter { album ->
//            viewModel.send(SelectSpotifyAlbumsActions.AlbumClicked(album))
//        }
//        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
//                adapter.submitList(it.albums)
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.uiEffect.consumeAsFlow()
                .collect { effect ->

//                    if (effect is UiEffect.ReadyUiEffect) {
//                        Handler(Looper.getMainLooper()).postDelayed({
//                            binding.selectAllBannerView.isVisible = true
//                        }, 500)
//                    }

                    if (effect is UiEffect.SuccessUiEffect) {
                        viewModel.navigationFlowBus.send(NavigationFlow.Albums)
                    }
                }
        }
//
//        binding.errorScreen.setActionButtonClick {
//            viewModel.send(SelectSpotifyAlbumsActions.FetchSpotifyAlbums)
//        }
//
//        setupBanner()

        viewModel.send(SelectSpotifyAlbumsActions.FetchSpotifyAlbums)
    }

//    private fun setupBanner() {
//        binding.selectAllBannerView.setNegativeButtonClick {
//            binding.selectAllBannerView.isVisible = false
//        }
//
//        binding.selectAllBannerView.setPositiveButtonClick {
//            viewModel.send(SelectSpotifyAlbumsActions.SelectAllAlbums)
//            binding.selectAllBannerView.isVisible = false
//        }
//    }
}