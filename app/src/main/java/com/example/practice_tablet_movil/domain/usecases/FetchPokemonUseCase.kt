package com.example.practice_tablet_movil.domain.usecases

import com.example.practice_tablet_movil.data.PokemonRepository

class FetchPokemonUseCase(val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(id: Int) = pokemonRepository.getDetailPokemon(id)
}