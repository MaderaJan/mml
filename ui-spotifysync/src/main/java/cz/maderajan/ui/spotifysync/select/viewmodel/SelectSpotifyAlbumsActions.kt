package cz.maderajan.ui.spotifysync.select.viewmodel

import cz.maderajan.common.ui.viewmodel.IAction
import cz.maderajan.ui.spotifysync.SelectableAlbum

sealed class SelectSpotifyAlbumsActions : IAction

object SyncSpotifyAlbums : SelectSpotifyAlbumsActions()
class AlbumClicked(val album: SelectableAlbum) : SelectSpotifyAlbumsActions()