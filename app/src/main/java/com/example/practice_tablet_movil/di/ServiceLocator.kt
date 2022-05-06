package com.example.practice_tablet_movil.di

import com.example.practice_tablet_movil.data.PokemonRepository
import com.example.practice_tablet_movil.data.network.ApiClient
import com.example.practice_tablet_movil.data.network.PokemonDataSource
import com.example.practice_tablet_movil.ui.viewmodel.PokemonViewModel

object ServiceLocator {
    private var apiClient: ApiClient? = null
    private var pokemonRepository: PokemonRepository? = null

    private fun createApiClient(): ApiClient {
        val apiClientTmp = ApiClient()
        apiClient = apiClientTmp
        return apiClientTmp
    }

    private fun createPokemonRepository(): PokemonRepository {
        val pokemonRepositoryTmp = PokemonDataSource(provideApiclient())
        pokemonRepository = pokemonRepositoryTmp
        return pokemonRepositoryTmp
    }

    private fun provideApiclient() = apiClient ?: createApiClient()
    fun pokemonRepository() = pokemonRepository ?: createPokemonRepository()
    fun destroy() {
        apiClient = null
        pokemonRepository = null
    }
}