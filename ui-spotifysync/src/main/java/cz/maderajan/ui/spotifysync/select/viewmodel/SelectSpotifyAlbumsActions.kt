package cz.maderajan.ui.spotifysync.select.viewmodel

import cz.maderajan.common.ui.viewmodel.IAction

sealed class SelectSpotifyAlbumsActions : IAction

object SyncSpotifyAlbums : SelectSpotifyAlbumsActions()