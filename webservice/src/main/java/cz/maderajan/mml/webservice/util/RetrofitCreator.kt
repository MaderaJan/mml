package cz.maderajan.mml.webservice.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitCreator {

    inline fun <reified T> create(baseUrl: String, okHttpClient: OkHttpClient): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(T::class.java)
}