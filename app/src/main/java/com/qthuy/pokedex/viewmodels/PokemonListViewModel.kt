package com.qthuy.pokedex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.qthuy.pokedex.data.PokemonRepository
import com.qthuy.pokedex.data.network.PokemonServices
import com.qthuy.pokedex.models.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PokemonListViewModel(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemons: Flow<PagingData<Pokemon>>

    val pokemons
        get() = _pokemons

    init {
        _pokemons = fetchPokemon()
    }

    private fun fetchPokemon() : Flow<PagingData<Pokemon>> = repository.getSearchResultStream()

}
class PokemonListViewModelFactory (private val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonListViewModel(repository) as T
    }
}