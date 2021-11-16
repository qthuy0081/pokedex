package com.qthuy.pokedex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qthuy.pokedex.data.network.PokemonAPI
import com.qthuy.pokedex.models.Pokemon
import com.qthuy.pokedex.models.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {
    private val _response = MutableLiveData<List<Pokemon>>()

    val response
        get() = _response
    init {

        fecthPokemons()
    }

    private fun fecthPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _response.postValue(PokemonAPI.retrofitServices.fetchPokemonList().results)
            } catch (e: Error) {
                throw Error()
            }
        }
    }
}