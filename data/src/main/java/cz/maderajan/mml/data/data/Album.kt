package cz.maderajan.mml.data.data

data class Album(
    val id: String,
    val name: String,
    val image: String?,
    val artists: List<Artist>,
    val genres: List<String>
) {

    override fun toString(): String {
        return "Album(id='$id', name='$name', image=$image, artists=$artists, genres=$genres)"
    }
}