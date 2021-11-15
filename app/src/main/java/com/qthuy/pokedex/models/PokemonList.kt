package com.qthuy.pokedex.models

import com.squareup.moshi.Json

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Pokemon>
)
data class Pokemon(
    val name: String,
    @Json(name = "url") val infoURL: String

)
