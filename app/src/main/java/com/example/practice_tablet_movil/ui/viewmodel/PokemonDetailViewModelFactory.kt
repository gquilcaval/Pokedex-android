package com.example.practice_tablet_movil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practice_tablet_movil.data.PokemonRepository
import com.example.practice_tablet_movil.domain.usecases.FetchPokemonUseCase


class PokemonDetailViewModelFactory(private val fetchPokemonUseCase: FetchPokemonUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonDetailViewModel(fetchPokemonUseCase) as T
    }
}
