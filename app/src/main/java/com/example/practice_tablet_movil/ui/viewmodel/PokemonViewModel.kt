package com.example.practice_tablet_movil.ui.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_tablet_movil.data.network.model.PokeResult
import com.example.practice_tablet_movil.data.network.model.Pokemon
import com.example.practice_tablet_movil.domain.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase

) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    private val _ListaPokemon = MutableLiveData<List<PokeResult>>()
    val listaPokemon: LiveData<List<PokeResult>>
        get() = _ListaPokemon

    init {
        onCreate()
    }

    fun onCreate() {
        isLoading.postValue(true)
        viewModelScope.launch {


            val result = getPokemonUseCase()

            if (result != null){
                _ListaPokemon.postValue(result)
                isLoading.postValue(false)
            }


        }
    }

}