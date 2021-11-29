package com.qthuy.pokedex.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.qthuy.pokedex.data.network.PokemonServices
import com.qthuy.pokedex.models.Pokemon

private const val START_INDEX = 0
class PokemonPagingSource(private val services: PokemonServices) : PagingSource<Int, Pokemon>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val position = params.key ?: START_INDEX
        try {
            val response = services.fetchPokemonList(limit = params.loadSize, offset = position * params.loadSize)
            val pokemons = response.results
            return LoadResult.Page(
                data = pokemons,
                prevKey = if(position == START_INDEX) null else position - 1,
                nextKey = if (pokemons.isEmpty()) null else position + 1
            )

        } catch (e: Exception) {
          return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}