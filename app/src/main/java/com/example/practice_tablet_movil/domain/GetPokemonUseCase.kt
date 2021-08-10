package com.example.practice_tablet_movil.domain

import com.example.practice_tablet_movil.data.network.model.PokeResult
import com.example.practice_tablet_movil.data.network.model.Pokemon
import com.example.practice_tablet_movil.repository.PokemonRepository

class GetPokemonUseCase {

    private val repository = PokemonRepository()

    suspend operator fun invoke():List<PokeResult>? = repository.getAllPokemon()

}