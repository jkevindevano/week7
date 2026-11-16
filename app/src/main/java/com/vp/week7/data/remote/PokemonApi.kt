package com.vp.week7.data.remote

import com.vp.week7.data.dto.PokemonSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("v2/cards")
    suspend fun cariKartuPokemon(
        @Query("search") kataKunci: String,
        @Query("limit") batasData: Int = 20
    ): PokemonSearchResponseDto
}
