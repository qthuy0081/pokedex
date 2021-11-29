package com.qthuy.pokedex.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.qthuy.pokedex.data.network.PokemonServices
import com.qthuy.pokedex.models.Pokemon
import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val services: PokemonServices) {


    fun getSearchResultStream(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = true, pageSize = 19),
            pagingSourceFactory = {PokemonPagingSource(services)}
        ).flow
    }
}