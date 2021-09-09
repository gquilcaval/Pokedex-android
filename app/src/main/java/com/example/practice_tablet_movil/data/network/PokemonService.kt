package com.example.practice_tablet_movil.data.network

import com.example.practice_tablet_movil.core.RetrofitHelper
import com.example.practice_tablet_movil.data.network.model.Pokemon
import com.example.practice_tablet_movil.data.network.model.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PokemonService @Inject constructor(

    private  val api: PokemonApi
){

    //private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAllPokemonService(): PokemonResponse{
        return  withContext(Dispatchers.IO){
            val response: Response<PokemonResponse> = api.getPokeomBody(100,0)
            response.body() ?:  PokemonResponse(emptyList())
        }
    }

    suspend fun getDetailPokemonService( id: Int): Pokemon{
        return  withContext(Dispatchers.IO){
            val response: Response<Pokemon> = api.getDetailPokemonBody(id)
            response.body() ?:  Pokemon(0,"",0,0 , emptyList(), emptyList())
        }
    }
}