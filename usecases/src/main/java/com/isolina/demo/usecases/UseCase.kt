package com.isolina.demo.usecases

import com.isolina.demo.domain.base.Output
import com.isolina.demo.domain.models.Beer

interface UseCase {
    suspend fun executeBeers(): Output<List<Beer>>

    suspend fun executeSearchBeers(beerName: String): Output<List<Beer>>
}