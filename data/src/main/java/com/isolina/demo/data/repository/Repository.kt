package com.isolina.demo.data.repository

import com.isolina.demo.domain.base.Output
import com.isolina.demo.domain.models.Beer

interface Repository {
    suspend fun beers(): Output<List<Beer>> = beers()

    suspend fun searchBeers(beerName: String): Output<List<Beer>> = searchBeers(beerName)
}