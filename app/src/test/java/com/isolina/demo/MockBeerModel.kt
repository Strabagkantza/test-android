package com.isolina.demo

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.isolina.demo.domain.base.Output
import com.isolina.demo.domain.models.Beer
import java.io.File

fun getJson(): List<Beer> {
    val jsonFile = File("src/test/resources/response.json")
    val json = jsonFile.readText()

    val gson = Gson()
    val beerListType = object : TypeToken<List<Beer>>() {}.type
    return gson.fromJson(json, beerListType)
}

fun getResponse(): Output<List<Beer>> {
     return Output(status = Output.Status.SUCCESS, data = getJson(), message = "", error = null, headers = null)
}