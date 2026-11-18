package com.vp.week7.data.remote

import com.vp.week7.data.repository.PokemonRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

object ServiceLocator {
    private const val BASE_URL = "https://www.pokemonpricetracker.com/api/"

    private const val API_KEY = "pokeprice_free_da35cbcea7615e61bcc7a116c88fe9db22b6ebf44c01151c"

    private val jsonParser = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(API_KEY))
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonParser.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val pokemonApi: PokemonApi by lazy {
        retrofit.create(PokemonApi::class.java)
    }

    val pokemonRepository: PokemonRepository by lazy {
        PokemonRepository(pokemonApi)
    }
}
