package com.qthuy.pokedex.data.network

import com.qthuy.pokedex.models.PokemonResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonServices {
    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ) : PokemonResponse
    companion object {
        private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val  BASE_URL = "https://pokeapi.co/api/v2/"
        fun create(): PokemonServices {
             val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
                    BASE_URL).build()
            return  retrofit.create(PokemonServices::class.java)
        }
    }
}







