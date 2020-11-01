package cz.maderajan.mml.webservice.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class OkHttpCreator {

    fun create(): OkHttpClient =
        OkHttpClient.Builder().apply {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            addInterceptor(logging)
        }.build()
}