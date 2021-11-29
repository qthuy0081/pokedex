package com.qthuy.pokedex

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qthuy.pokedex.adapters.PokemonLoadStateAdapter
import com.qthuy.pokedex.adapters.PokemonRecyclerAdapter
import com.qthuy.pokedex.viewmodels.PokemonListViewModel
import com.qthuy.pokedex.viewmodels.PokemonListViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PokemonListFragment : Fragment() {

    private val viewModel: PokemonListViewModel by viewModels  {
        PokemonListViewModelFactory((activity?.application as MainApplication).repository)
    }


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
        val processIndicator: ProgressBar =  view.findViewById(R.id.progress_circular)
        val retryButton: Button = view.findViewById(R.id.button_retry)
        val layoutManager = GridLayoutManager(context, 2)
        val adapter = PokemonRecyclerAdapter()

        recyclerView.layoutManager = layoutManager
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {

                return if (adapter.getItemViewType(position) == 0) 2 else 1

            }
        }
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PokemonLoadStateAdapter { adapter.retry() } ,
            footer = PokemonLoadStateAdapter { adapter.retry() }
        )

        retryButton.setOnClickListener {
            adapter.retry()
        }

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.pokemons.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                val isEmptyList = it.refresh is LoadState.NotLoading && adapter.itemCount == 0
                recyclerView.isVisible = !isEmptyList
                processIndicator.isVisible = it.source.refresh is LoadState.Loading
                retryButton.isVisible = it.source.refresh is LoadState.Error
            }
        }

        return view
    }


}