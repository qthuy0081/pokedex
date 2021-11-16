package com.qthuy.pokedex

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.qthuy.pokedex.adapters.PokemonRecyclerAdapter
import com.qthuy.pokedex.viewmodels.PokemonListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonListFragment : Fragment() {



    private val viewModel: PokemonListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView_pokemon)
        val adapter = PokemonRecyclerAdapter()
        recyclerView.adapter = adapter
        viewModel.response.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return view
    }


}