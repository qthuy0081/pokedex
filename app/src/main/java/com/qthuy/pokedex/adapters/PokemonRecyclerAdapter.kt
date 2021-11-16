package com.qthuy.pokedex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.qthuy.pokedex.R
import com.qthuy.pokedex.databinding.ListItemPokemonBinding
import com.qthuy.pokedex.models.Pokemon
import com.qthuy.pokedex.models.PokemonList

class PokemonRecyclerAdapter: ListAdapter<Pokemon, PokemonRecyclerAdapter.ViewHolder>(PokemonDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(item = pokemon)
    }

    class ViewHolder(private val binding: ListItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pokemon) {
            binding.apply {
                binding.pokemon = item
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