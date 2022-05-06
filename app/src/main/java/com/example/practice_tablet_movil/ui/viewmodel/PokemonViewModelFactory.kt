package com.example.practice_tablet_movil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practice_tablet_movil.data.PokemonRepository
import com.example.practice_tablet_movil.domain.usecases.FetchAllPokemonUseCase


class PokemonViewModelFactory(private val fetchAllPokemonUseCase: FetchAllPokemonUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonViewModel(fetchAllPokemonUseCase) as T
    }
}
