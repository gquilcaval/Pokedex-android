package com.example.practice_tablet_movil.repository

import com.example.practice_tablet_movil.data.network.PokemonService
import com.example.practice_tablet_movil.data.network.model.PokeResult
import com.example.practice_tablet_movil.data.network.model.Pokemon
import kotlinx.coroutines.delay


class PokemonRepository {

    private val api = PokemonService()

    suspend fun getAllPokemon():List<PokeResult>{

        val reponse = api.getAllPokemonService().results
        return reponse
    }
    suspend fun getDetailPokemon(id: Int): Pokemon {
        delay(1000)
        val reponse = api.getDetailPokemonService(id)
        return reponse
    }
}