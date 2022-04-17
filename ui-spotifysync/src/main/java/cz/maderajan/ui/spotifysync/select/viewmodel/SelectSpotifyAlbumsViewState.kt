package cz.maderajan.ui.spotifysync.select.viewmodel

import cz.maderajan.common.ui.viewmodel.BaseViewState
import cz.maderajan.ui.spotifysync.data.select.ISelectableAlbum
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum

data class SelectSpotifyAlbumsViewState(
    val displayedAlbums: List<ISelectableAlbum>,
    val allAlbums: List<SelectableAlbum>,
    val selectedAlbums: List<SelectableAlbum>,
    val showBanner: Boolean = true,
    val filterValue: String = ""
) : BaseViewState