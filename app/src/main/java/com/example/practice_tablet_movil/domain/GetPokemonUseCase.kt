package com.example.practice_tablet_movil.domain

import com.example.practice_tablet_movil.data.network.model.PokeResult
import com.example.practice_tablet_movil.data.network.model.Pokemon
import com.example.practice_tablet_movil.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val repository : PokemonRepository
) {



    suspend operator fun invoke():List<PokeResult>? = repository.getAllPokemon()

}