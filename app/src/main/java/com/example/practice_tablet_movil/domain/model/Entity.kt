package com.example.practice_tablet_movil.domain.model

data class Pokemon (val id: String, val name: String, val weight: Int, val height: Int,val types:List<TypeBase>, val typeOptional: List<String>, val stats: List<StatBase>)
data class StatBase (val base_stat:Int,val stat: Stat)
data class Stat (val name: String)
data class TypeBase (val slot: Int, val type: Type)
data class Type (val name: String)