package com.qthuy.pokedex

import android.app.Application
import com.qthuy.pokedex.data.PokemonRepository
import com.qthuy.pokedex.data.network.PokemonServices

class MainApplication : Application() {
    private val service = PokemonServices.create()
    val repository by lazy { PokemonRepository(service) }
}