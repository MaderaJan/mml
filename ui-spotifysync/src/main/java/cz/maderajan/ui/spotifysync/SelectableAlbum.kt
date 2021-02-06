package cz.maderajan.ui.spotifysync

import cz.maderajan.mml.data.data.Album
import cz.maderajan.mml.data.data.Artist

data class SelectableAlbum(
    override val id: String,
    override val name: String,
    override val image: String?,
    override val artists: List<Artist>,
    override val genres: List<String>,
    val isSelected: Boolean
) : Album(id, name, image, artists, genres) {

    val presentableArtist: String = artists.joinToString(", ") { it.name }
}