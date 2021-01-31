package cz.maderajan.mml.webservice.response

data class ArtistResponse(
    val id: String,
    val name: String,
    val images: List<ImagesResponse>?
)
