package com.example.practice_tablet_movil.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_tablet_movil.data.OperationResult
import com.example.practice_tablet_movil.data.PokemonRepository
import com.example.practice_tablet_movil.domain.model.Pokemon
import com.example.practice_tablet_movil.domain.usecases.FetchAllPokemonUseCase
import com.example.practice_tablet_movil.domain.usecases.FetchPokemonUseCase

import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val fetchPokemonUseCase: FetchPokemonUseCase) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getData(id: Int){
        viewModelScope.launch {
            if (_isLoading.value == null){
                _isLoading.value = true
                when(val data = fetchPokemonUseCase(id)){
                    is OperationResult.Success -> success(data.data)
                    is OperationResult.Failure -> failure(data.exception.toString())
                }
            }
        }
    }

    private fun success(entity: Pokemon) {
        _pokemon.value = entity
        _isLoading.value = false
    }

    private fun failure(exception: String) {
        _error.value = exception
        _isLoading.value = false
    }


}