package cz.maderajan.ui.spotifysync.select.viewmodel

import cz.maderajan.common.ui.viewmodel.BaseViewModelState
import cz.maderajan.ui.spotifysync.SelectableAlbum

data class SelectSpotifyAlbumsViewState(
    val albums: List<SelectableAlbum>
) : BaseViewModelState