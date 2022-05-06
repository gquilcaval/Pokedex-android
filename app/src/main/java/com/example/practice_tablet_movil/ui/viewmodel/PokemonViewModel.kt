package com.example.practice_tablet_movil.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_tablet_movil.data.OperationResult
import com.example.practice_tablet_movil.data.PokemonRepository

import com.example.practice_tablet_movil.domain.model.Pokemon
import com.example.practice_tablet_movil.domain.usecases.FetchAllPokemonUseCase
import kotlinx.coroutines.launch


class PokemonViewModel(private val fetchAllPokemonUseCase: FetchAllPokemonUseCase) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listaPokemon = MutableLiveData<List<Pokemon>>()
    val listaPokemon: LiveData<List<Pokemon>> = _listaPokemon

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getData(){
        viewModelScope.launch {
            if (_isLoading.value == null) {
                _isLoading.value = true
                when(val data = fetchAllPokemonUseCase()){
                    is OperationResult.Success -> success(data.data)
                    is OperationResult.Failure -> failure(data.exception.toString())
                }
            }

        }
    }

    fun success(list: List<Pokemon>) {
        _listaPokemon.value = list.toList()
        _isLoading.value = false
    }

    fun failure(exception: String) {
        _error.value = exception
        _isLoading.value = false
    }
}