package com.example.practice_tablet_movil.data

import com.example.practice_tablet_movil.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getAllPokemon(): OperationResult<List<Pokemon>>
    suspend fun getDetailPokemon(id: Int): OperationResult<Pokemon>
}