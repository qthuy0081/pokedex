package com.qthuy.pokedex.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.qthuy.pokedex.MyPokemonFragment
import com.qthuy.pokedex.PokemonListFragment


const val MY_POKEMON_PAGE_INDEX = 0
const val POKEMON_LIST_PAGE_INDEX = 1
class PokedexPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentCreator: Map<Int, () -> Fragment> = mapOf(
        MY_POKEMON_PAGE_INDEX to { MyPokemonFragment() },
        POKEMON_LIST_PAGE_INDEX to { PokemonListFragment() }
    )

    override fun getItemCount(): Int = tabFragmentCreator.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}