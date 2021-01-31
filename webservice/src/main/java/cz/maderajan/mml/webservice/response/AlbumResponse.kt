package cz.maderajan.mml.webservice.response

data class AlbumResponse(
    val id: String,
    val name: String,
    val images: List<ImagesResponse>?,
    val artists: List<ArtistResponse>?,
    val genres: List<String>
)