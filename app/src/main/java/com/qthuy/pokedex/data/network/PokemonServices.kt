package com.qthuy.pokedex.data.network

import com.qthuy.pokedex.models.PokemonList
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
    ) : PokemonList

}
val  BASE_URL = "https://pokeapi.co/api/v2/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
    BASE_URL).build()


object PokemonAPI {
    val retrofitServices: PokemonServices by lazy {
        retrofit.create(PokemonServices::class.java)
    }
}