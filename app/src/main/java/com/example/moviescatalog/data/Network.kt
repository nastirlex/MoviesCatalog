package com.example.moviescatalog.data

import com.example.moviescatalog.utils.Constants.Companion.BASE_URL
import com.example.moviescatalog.data.authapi.AuthApi
import com.example.moviescatalog.data.authapi.AuthInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object Network {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private fun getHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(AuthInterceptor()) //для токена
            //Authenticator
            val logLevel = HttpLoggingInterceptor.Level.BODY
            addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
        }
        return client.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(getHttpClient())
            .build()

        return retrofit
    }

    private val retrofit: Retrofit = getRetrofit()

    var token: TokenResponse? = null

    fun getAuthApi(): AuthApi = retrofit.create(AuthApi::class.java)
}