package com.qthuy.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.qthuy.pokedex.R
import com.qthuy.pokedex.databinding.PokemonLoadStateFooterViewItemBinding

class PokemonLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<PokemonLoadStateHolder>() {
    override fun onBindViewHolder(holder: PokemonLoadStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PokemonLoadStateHolder {
        return PokemonLoadStateHolder.create(parent, retry)
    }


}
class PokemonLoadStateHolder(
    private val binding: PokemonLoadStateFooterViewItemBinding,
    retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.buttonRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if(loadState is LoadState.Error) {
            binding.textViewError.text = loadState.error.localizedMessage
        }
        binding.progressItem.isVisible = loadState is LoadState.Loading
        binding.buttonRetry.isVisible = loadState is LoadState.Error
        binding.textViewError.isVisible = loadState is LoadState.Error
    }
    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PokemonLoadStateHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pokemon_load_state_footer_view_item, parent, false)
            val binding = PokemonLoadStateFooterViewItemBinding.bind(view)
            return PokemonLoadStateHolder(binding, retry)
        }
    }
}
