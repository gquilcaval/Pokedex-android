package com.example.practice_tablet_movil.data.network

import com.example.practice_tablet_movil.data.network.model.Pokemon
import com.example.practice_tablet_movil.data.network.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

     @GET("pokemon")
    suspend fun getPokeomBody(@Query("limit")limit: Int,@Query("offset") offset: Int):Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getDetailPokemonBody(@Path("id")id: Int):Response<Pokemon>
}