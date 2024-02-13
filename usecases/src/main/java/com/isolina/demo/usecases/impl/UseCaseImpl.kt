package com.isolina.demo.usecases.impl

import com.isolina.demo.data.repository.Repository
import com.isolina.demo.domain.base.Output
import com.isolina.demo.domain.models.Beer
import com.isolina.demo.usecases.UseCase
import javax.inject.Inject

internal class UseCaseImpl @Inject constructor(
    private val repository: Repository
) : UseCase {

    override suspend fun executeBeers(): Output<List<Beer>> {
        val res = repository.beers()
        if (res.status == Output.Status.SUCCESS) {
            res.data?.let {
                return Output(status = res.status, data = it, message = res.message, error = res.error, headers = res.headers)
            }
        }
        return Output(status = res.status, data = null, message = res.message, error = res.error, headers = null)
    }

    override suspend fun executeSearchBeers(beerName: String): Output<List<Beer>> {
        val res = repository.searchBeers(beerName)
        if (res.status == Output.Status.SUCCESS) {
            res.data?.let {
                return Output(status = res.status, data = it, message = res.message, error = res.error, headers = res.headers)
            }
        }
        return Output(status = res.status, data = null, message = res.message, error = res.error, headers = null)
    }

}