package com.example.practice_tablet_movil.ui.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_tablet_movil.data.network.model.PokeResult
import com.example.practice_tablet_movil.data.network.model.Pokemon
import com.example.practice_tablet_movil.domain.GetPokemonUseCase
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {


    var getPokemonUseCase = GetPokemonUseCase()
    private val _ListaPokemon = MutableLiveData<List<PokeResult>>()
    val listaPokemon: LiveData<List<PokeResult>>
        get() = _ListaPokemon

    init {
        onCreate()
    }

    fun onCreate() {

        viewModelScope.launch {


            val result = getPokemonUseCase()


            _ListaPokemon.postValue(result)

        }
    }

}