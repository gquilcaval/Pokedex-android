package com.example.practice_tablet_movil.ui.view.adapter

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.practice_tablet_movil.R
import com.example.practice_tablet_movil.data.network.model.PokeResult
import com.example.practice_tablet_movil.data.network.model.Pokemon
import com.example.practice_tablet_movil.databinding.CardviewPokemonBinding

class PokemonAdapter(private val pokemons: List<PokeResult>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.cardview_pokemon, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nombre.text = pokemons[position].name
        Glide.with(holder.itemView).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(position+1)+".png")
           .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
           .into(holder.image)

        holder.cardview.setOnClickListener {
            val bundle = Bundle()

            bundle.putInt("id", (position+1))

            Navigation.findNavController(it).navigate(R.id.action_pokemonsFragment_to_detailPokemonFragment,bundle)
        }



    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = CardviewPokemonBinding.bind(itemView)

        val image = binding.imagePokemon
        val nombre = binding.tvNombre
        val cardview = binding.cardviewPokemon


    }


}