package com.isolina.demo.data.impl

import com.isolina.demo.data.remote.RepositoryDataSource
import com.isolina.demo.data.repository.Repository
import com.isolina.demo.domain.base.Output
import com.isolina.demo.domain.models.Beer
import javax.inject.Inject

internal class RepositoryImpl @Inject constructor(
    private val repositoryDataSource: RepositoryDataSource
) : Repository {
    override suspend fun beers(): Output<List<Beer>> = repositoryDataSource.beers()

    override suspend fun searchBeers(beerName: String): Output<List<Beer>> = repositoryDataSource.searchBeers(beerName)
}