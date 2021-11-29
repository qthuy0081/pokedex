package com.qthuy.pokedex.models

import com.squareup.moshi.Json

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Pokemon>
)
data class Pokemon(
    val name: String,
    @Json(name = "url") val infoURL: String
) {
    fun getImageURL(): String {
        val index = infoURL.split("/").dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${index}.png"
    }
}
