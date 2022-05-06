package com.example.practice_tablet_movil.utils

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.practice_tablet_movil.R
import java.util.*

class PokemonColorUtil(var context: Context) {


    @ColorInt
    fun getPokemonColor(typeOfPokemon: List<String>?): Int {
        val type = typeOfPokemon?.getOrNull(0)
        val color = when (type?.toLowerCase(Locale.ROOT)) {
            "grass", "bug" -> R.color.grass
            "fire" -> R.color.fire
            "water", "fighting", "normal" -> R.color.water
            "electric", "psychic" -> R.color.electric
            "poison", "ghost" -> R.color.poison
            "ground", "rock" -> R.color.rock
            "dark" -> R.color.black
            else -> R.color.bug
        }
        return convertColor(color)
    }

    @ColorInt
    fun convertColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }
}