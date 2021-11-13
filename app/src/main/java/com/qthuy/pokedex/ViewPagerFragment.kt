package com.qthuy.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.qthuy.pokedex.adapters.MY_POKEMON_PAGE_INDEX
import com.qthuy.pokedex.adapters.POKEMON_LIST_PAGE_INDEX
import com.qthuy.pokedex.adapters.PokedexPagerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewPagerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val tabLayout: TabLayout = view.findViewById(R.id.tabs)
        val pager:ViewPager2 = view.findViewById(R.id.view_pager)
        pager.adapter = PokedexPagerAdapter(this)
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = getTabTile(position)
            tab.setIcon(getIcon(position))
        }.attach()
        return view
    }

    private fun getTabTile (position: Int): String? {
        return when (position) {
            MY_POKEMON_PAGE_INDEX -> "My Pokemon"
            POKEMON_LIST_PAGE_INDEX -> "Pokemon List"
            else -> null
        }
    }

    private fun getIcon (position: Int): Int {
        return when (position) {
            MY_POKEMON_PAGE_INDEX -> R.drawable.my_pokemon_tab_selector
            POKEMON_LIST_PAGE_INDEX -> R.drawable.pokemon_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }


}