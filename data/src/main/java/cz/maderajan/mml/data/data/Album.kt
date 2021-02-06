package cz.maderajan.mml.data.data

open class Album(
    open val id: String,
    open val name: String,
    open val image: String?,
    open val artists: List<Artist>,
    open val genres: List<String>
)