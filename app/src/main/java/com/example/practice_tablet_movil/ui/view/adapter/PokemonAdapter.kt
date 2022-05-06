package com.example.practice_tablet_movil.ui.view.adapter

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.practice_tablet_movil.R
import com.example.practice_tablet_movil.domain.model.Pokemon
import com.example.practice_tablet_movil.extensions.getImagePath
import com.example.practice_tablet_movil.media.WrapperImage
import com.example.practice_tablet_movil.utils.PokemonColorUtil


class PokemonAdapter(private var listPokemons: List<Pokemon>, private val selectItem: (id: Int, image: ImageView, idImage: String) -> Unit) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.imagePokemon)
        val id = view.findViewById<TextView>(R.id.tvId)
        val nombre = view.findViewById<TextView>(R.id.tvNombre)
        val type = view.findViewById<TextView>(R.id.tvType3)
        val container = view.findViewById<RelativeLayout>(R.id.relativeLayoutBackground)

        @SuppressLint("ResourceAsColor")
        fun bind(pokemon: Pokemon) {
            ViewCompat.setTransitionName(image, "img$adapterPosition")
            id.text = pokemon.id
            nombre.text = pokemon.name
            type.text = pokemon.typeOptional[0]
            WrapperImage.picasso.load(getImagePath(adapterPosition+1, 3), image)

            /*container.setCardBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(itemView.context, itemView.resources.getIdentifier("${pokemon.typeOptional[0].substring(0,1).lowercase()}${pokemon.typeOptional[0].substring(1,pokemon.typeOptional[0].length)}",
                "color", itemView.context.packageName))))*/
            val color = PokemonColorUtil(itemView.context).getPokemonColor(pokemon.typeOptional)
            container.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            itemView.setOnClickListener {
                selectItem(position, image, "img$adapterPosition")
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.cardview_pokemon, parent, false)
        return PokemonViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return listPokemons.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(listPokemons[position])
    }

    fun update(list: List<Pokemon>) {
        listPokemons = list
        notifyDataSetChanged()
    }

}