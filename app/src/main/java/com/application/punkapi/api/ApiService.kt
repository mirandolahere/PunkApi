package com.application.punkapi.api

import com.application.punkapi.beer.entity.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /*LISTA DE CERVEZAS*/
    @GET("beers")
    fun ListBeer(): Call<List<Beer>>

    /*BUSQUEDA SEGUN NOMBRE*/
    @GET("beers")
    fun SearchBeer(@Query("beer_name") code :String): Call<List<Beer>>
}