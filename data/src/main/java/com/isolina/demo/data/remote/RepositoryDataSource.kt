package com.isolina.demo.data.remote

import com.isolina.demo.data.base.BaseRemoteDataSource
import com.isolina.demo.data.remote.service.ApiService
import com.isolina.demo.domain.base.Output
import com.isolina.demo.domain.models.Beer
import retrofit2.Retrofit
import javax.inject.Inject

class RepositoryDataSource @Inject constructor(
    private val apiService: ApiService, retrofit: Retrofit
) : BaseRemoteDataSource(retrofit) {

    suspend fun beers(): Output<List<Beer>> {
        return getResponse(
            request = { apiService.beers() },
            defaultErrorMessage = "There was an error. Please try again"
        )
    }

    suspend fun searchBeers(beerName: String): Output<List<Beer>> {
        return getResponse(
            request = { apiService.searchBeers(beerName) },
            defaultErrorMessage = "There was an error. Please try again"
        )
    }
}