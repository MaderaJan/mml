package cz.maderajan.mml.webservice.response

data class PagingWrapper<T>(
    val items: List<T>,
    val total: Int,
    val next: String?
)