package cz.maderajan.ui.spotifysync.select.viewmodel

import cz.maderajan.common.ui.viewmodel.IAction
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum

sealed class SelectSpotifyAlbumsActions : IAction {
    object SyncSpotifyAlbums : SelectSpotifyAlbumsActions()
    object SelectAllAlbums : SelectSpotifyAlbumsActions()
    object SaveSelectedAlbums : SelectSpotifyAlbumsActions()

    class AlbumClicked(val album: SelectableAlbum) : SelectSpotifyAlbumsActions()
}