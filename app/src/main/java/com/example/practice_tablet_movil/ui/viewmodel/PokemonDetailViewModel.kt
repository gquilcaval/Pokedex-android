package com.example.practice_tablet_movil.ui.view.viewmodel

import android.app.UiModeManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_tablet_movil.data.network.model.PokeResult
import com.example.practice_tablet_movil.data.network.model.Pokemon
import com.example.practice_tablet_movil.domain.GetDetailPokemonUseCase
import com.example.practice_tablet_movil.domain.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getDetailPokemonUseCase: GetDetailPokemonUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    init {
        pokemon.value?.let { onCreate(it.id) }
    }

    fun onCreate(id:Int) {

        isLoading.postValue(true)
        viewModelScope.launch {



            val result = getDetailPokemonUseCase(id)

            if (result != null){
                _pokemon.postValue(result)
                isLoading.postValue(false)
            }



        }
    }

}