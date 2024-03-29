package cz.maderajan.ui.spotifysync.select.viewmodel

import cz.maderajan.common.ui.viewmodel.IAction
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum

sealed class SelectSpotifyAlbumsActions : IAction {
    object FetchSpotifyAlbums : SelectSpotifyAlbumsActions()
    object SelectAllAlbums : SelectSpotifyAlbumsActions()
    object SaveSelectedAlbums : SelectSpotifyAlbumsActions()
    object HideBanner : SelectSpotifyAlbumsActions()
    object OpenFilterAction : SelectSpotifyAlbumsActions()
    object ClearFilter : SelectSpotifyAlbumsActions()

    class FilterValueChanged(val value: String) : SelectSpotifyAlbumsActions()
    class AlbumClicked(val album: SelectableAlbum) : SelectSpotifyAlbumsActions()
}