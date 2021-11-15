package com.qthuy.pokedex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qthuy.pokedex.data.network.PokemonAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()

    val response
        get() = _response
    init {

        fecthPokemons()
    }

    private fun fecthPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _response.postValue(PokemonAPI.retrofitServices.fetchPokemonList().count.toString())
            } catch (e: Exception) {
                _response.value = e.message
            }
        }
    }
}