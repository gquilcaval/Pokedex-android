package com.example.practice_tablet_movil.domain.usecases

import com.example.practice_tablet_movil.data.OperationResult
import com.example.practice_tablet_movil.data.PokemonRepository
import com.example.practice_tablet_movil.domain.model.Pokemon

class FetchAllPokemonUseCase(val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke():OperationResult<List<Pokemon>> {
        return pokemonRepository.getAllPokemon()
    }
}