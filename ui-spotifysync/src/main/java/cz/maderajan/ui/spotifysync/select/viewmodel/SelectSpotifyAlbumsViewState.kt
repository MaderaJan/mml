package cz.maderajan.ui.spotifysync.select.viewmodel

import cz.maderajan.common.ui.viewmodel.BaseViewState
import cz.maderajan.ui.spotifysync.data.select.ISelectableAlbum

data class SelectSpotifyAlbumsViewState(
    val albums: List<ISelectableAlbum>
) : BaseViewState