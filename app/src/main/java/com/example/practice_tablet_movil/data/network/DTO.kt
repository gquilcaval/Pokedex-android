package com.example.practice_tablet_movil.data.network

import com.example.practice_tablet_movil.domain.model.*
import com.google.gson.annotations.SerializedName

data class PokeResultDTO(val id: String?, val name: String?, @SerializedName("typeofpokemon", )val type: List<String>)

data class PokemonDTO(val id: Int?, val name: String?,val url:String?, val weight: Int?, val height: Int?, val types:List<TypeBaseDTO>?, val stats: List<StatBaseDTO>?) {
    fun toPokemon() = Pokemon(id.toString(),name ?: "", weight ?: 0, height ?: 0,
        types?.map {
                TypeBase(it.slot, it.type.toType())
        } ?: emptyList() , emptyList(),
        stats?.map {
            StatBase(it.base_stat, it.stat.toStat())
        } ?: emptyList()
    )
}

data class StatBaseDTO (val base_stat:Int, val stat: StatDTO)
data class StatDTO (val name:String?) {
    fun toStat() = Stat(name ?: "")
}

data class TypeBaseDTO (val slot:Int, val type: TypeDTO)
data class TypeDTO (val name: String?) {
    fun toType() = Type(name ?: "")
}