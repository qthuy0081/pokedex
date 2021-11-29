package com.qthuy.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qthuy.pokedex.databinding.ListItemPokemonBinding
import com.qthuy.pokedex.models.Pokemon
import kotlinx.coroutines.flow.collectLatest

class PokemonRecyclerAdapter: PagingDataAdapter<Pokemon, PokemonRecyclerAdapter.ViewHolder>(PokemonDiffCallBack()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = getItem(position)
        if(pokemon != null) {
            holder.bind(item = pokemon)
        }
    }


    override fun getItemViewType(position: Int): Int {

        return if (position == itemCount) 0 else 1
    }

    class ViewHolder(private val binding: ListItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pokemon) {
            binding.apply {
                binding.pokemon = item
                val imageURL = item.getImageURL()
                Glide.with(binding.root).load(imageURL).into(binding.cardImage)
                executePendingBindings()
            }
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPokemonBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
private class PokemonDiffCallBack : DiffUtil.ItemCallback<Pokemon>() {

    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}