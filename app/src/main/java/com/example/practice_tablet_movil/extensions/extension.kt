package com.example.practice_tablet_movil.extensions

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.practice_tablet_movil.R
import java.util.*

fun Any.getImagePath(number: Int, length: Int): String {
    var str = "$number"
    while (str.length < length) {
        str = "0$str"
    }
    return "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$str.png"
}



