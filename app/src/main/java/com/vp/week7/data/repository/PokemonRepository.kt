package com.vp.week7.data.repository

import com.vp.week7.data.remote.PokemonApi
import com.vp.week7.model.PokemonCard
import com.vp.week7.util.Resource
import com.vp.week7.util.safeApiCall

class PokemonRepository(private val api: PokemonApi) {

    private val cacheKartu = mutableMapOf<String, List<PokemonCard>>()

    suspend fun cariKartuPokemon(kataKunci: String): Resource<List<PokemonCard>> {
        val kataKunciBersih = kataKunci.trim().lowercase()

        val dataCache = cacheKartu[kataKunciBersih]
        if (dataCache != null && dataCache.isNotEmpty()) {
            return Resource.Success(dataCache)
        }

        val hasil = safeApiCall {
            val response = api.cariKartuPokemon(kataKunci = kataKunci.trim(), batasData = 20)
            val daftarKartu = response.daftarKartu?.map { it.keModelDomain() } ?: emptyList()
            daftarKartu
        }

        if (hasil is Resource.Success && hasil.data.isNotEmpty()) {
            cacheKartu[kataKunciBersih] = hasil.data
        }

        return hasil
    }
}
