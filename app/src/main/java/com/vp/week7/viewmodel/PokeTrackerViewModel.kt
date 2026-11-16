package com.vp.week7.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vp.week7.data.remote.ServiceLocator
import com.vp.week7.data.repository.PokemonRepository
import com.vp.week7.model.PokemonCard
import com.vp.week7.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokeTrackerViewModel(
    private val repository: PokemonRepository = ServiceLocator.pokemonRepository
) : ViewModel() {

    val daftarChipFilter = listOf("Charizard", "Pikachu", "Mewtwo", "Gengar")

    private val _kataKunciPencarian = MutableStateFlow("Charizard")
    val kataKunciPencarian: StateFlow<String> = _kataKunciPencarian.asStateFlow()

    private val _uiState = MutableStateFlow<Resource<List<PokemonCard>>>(Resource.Loading)
    val uiState: StateFlow<Resource<List<PokemonCard>>> = _uiState.asStateFlow()

    init {
        cariKartu("Charizard")
    }

    fun ubahKataKunci(kataBaru: String) {
        _kataKunciPencarian.value = kataBaru
    }

    fun pilihChip(namaPokemon: String) {
        _kataKunciPencarian.value = namaPokemon
        cariKartu(namaPokemon)
    }

    fun cariKartu(kataKunci: String = _kataKunciPencarian.value) {
        if (kataKunci.isBlank()) return
        viewModelScope.launch {
            _uiState.value = Resource.Loading
            _uiState.value = repository.cariKartuPokemon(kataKunci.trim())
        }
    }

    fun ambilKartuBerdasarkanId(idKartu: String): PokemonCard? {
        val stateSaatIni = _uiState.value
        return if (stateSaatIni is Resource.Success) {
            stateSaatIni.data.find { it.idKartu == idKartu }
        } else {
            null
        }
    }
}
