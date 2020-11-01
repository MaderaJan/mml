package cz.maderajan.mml.webservice.util

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitCreator {

    @ExperimentalSerializationApi
    inline fun <reified T> create(baseUrl: String, okHttpClient: OkHttpClient): T {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(T::class.java)
    }
}