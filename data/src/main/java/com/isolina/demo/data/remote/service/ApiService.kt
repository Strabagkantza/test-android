package com.isolina.demo.data.remote.service

import com.isolina.demo.domain.models.Beer
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("beers")
    suspend fun beers(): Response<List<Beer>>

    @GET("beers")
    suspend fun searchBeers(@Query("beer_name") beerName: String): Response<List<Beer>>

}