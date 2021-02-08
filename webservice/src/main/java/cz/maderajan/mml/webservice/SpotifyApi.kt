package cz.maderajan.mml.webservice

import cz.maderajan.mml.webservice.response.AlbumWrapperResponse
import cz.maderajan.mml.webservice.response.PagingWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SpotifyApi {

    @GET("me/albums")
    suspend fun getAlbums(
        @Header("Authorization") token: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PagingWrapper<AlbumWrapperResponse>>
}