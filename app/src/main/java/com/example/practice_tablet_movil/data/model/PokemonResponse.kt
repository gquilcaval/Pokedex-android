package com.example.practice_tablet_movil.data.network.model

data class PokemonResponse (val results: List<PokeResult>)
data class PokeResult(val name: String, val url:String)