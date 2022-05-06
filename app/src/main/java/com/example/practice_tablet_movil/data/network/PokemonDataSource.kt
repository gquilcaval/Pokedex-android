package com.example.practice_tablet_movil.data.network

import com.example.practice_tablet_movil.data.OperationResult
import com.example.practice_tablet_movil.data.PokemonRepository
import com.example.practice_tablet_movil.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import kotlin.Exception

class PokemonDataSource(private val service: ApiClient): PokemonRepository {
    override suspend fun getAllPokemon(): OperationResult<List<Pokemon>> = withContext(Dispatchers.IO) {
        try {
            val response = service.build().getAllPokemon()
            if (response.isSuccessful) {
                val data = response.body()?.map {
                    Pokemon(it.id ?: "", it.name ?: "",  0,  0, emptyList() , it.type, emptyList() )
                }
                OperationResult.Success(data ?: emptyList())
            }
            else {
                OperationResult.Failure(Exception(response.errorBody().toString()))
            }
        }catch (exception: Exception) {
            OperationResult.Failure(exception)
        }
    }

    override suspend fun getDetailPokemon(id: Int): OperationResult<Pokemon> = withContext(Dispatchers.IO) {
        try {
            val response = service.buildPokeApi().getDetailPokemon(id)
            if (response.isSuccessful) {
                val data = response?.body()?.toPokemon()
                OperationResult.Success(data ?: Pokemon("","",0,0, emptyList(), emptyList(),emptyList()) )
            }
            else {
                OperationResult.Failure(Exception(response.errorBody().toString()))
            }
        }catch (exception: Exception) {
            OperationResult.Failure(exception)
        }
    }


}