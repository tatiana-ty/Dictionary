package ru.geekbrains.dictionary.model.datasource.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.dictionary.model.entities.DataModel

interface ApiService {

    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<DataModel>
}
