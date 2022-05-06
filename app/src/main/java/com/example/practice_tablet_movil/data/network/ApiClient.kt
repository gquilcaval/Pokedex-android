package com.example.practice_tablet_movil.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class ApiClient {
    private  val URL = "https://gist.githubusercontent.com/mrcsxsiq/b94dbe9ab67147b642baa9109ce16e44/raw/"
    private  val URLPokeApi = "https://pokeapi.co/api/v2/"
    private lateinit var serviceApi: ServiceApi
    private lateinit var servicePokeApi: ServicePokeApi

    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }


    fun build(): ServiceApi {
        val builder = Retrofit.Builder().apply {
            this.baseUrl(URL)
            this.addConverterFactory(GsonConverterFactory.create())
        }

        //TODO development
        val httpClient = OkHttpClient.Builder().let {
            it.addInterceptor(interceptor)
            it.build()
        }

        val retrofit = builder.let {
            it.client(httpClient)
            it.build()
        }
        serviceApi = retrofit.create(ServiceApi::class.java)

        return serviceApi
    }

    fun buildPokeApi(): ServicePokeApi {
        val builder = Retrofit.Builder().apply {
            this.baseUrl(URLPokeApi)
            this.addConverterFactory(GsonConverterFactory.create())
        }

        //TODO development
        val httpClient = OkHttpClient.Builder().let {
            it.addInterceptor(interceptor)
            it.build()
        }

        val retrofit = builder.let {
            it.client(httpClient)
            it.build()
        }
        servicePokeApi = retrofit.create(ServicePokeApi::class.java)

        return servicePokeApi
    }

    interface ServiceApi {
        /* @GET("pokemon")
        suspend fun getPokeomBody(@Query("limit")limit: Int,@Query("offset") offset: Int):Response<PokemonResponse>*/
        @GET("pokemon.json")
        suspend fun getAllPokemon(): Response<List<PokeResultDTO>>
    }

    interface ServicePokeApi {
        @GET("pokemon/{id}")
        suspend fun getDetailPokemon(@Path("id")id: Int):Response<PokemonDTO>
    }
}