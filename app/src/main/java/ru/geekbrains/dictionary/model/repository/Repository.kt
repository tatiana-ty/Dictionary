package ru.geekbrains.dictionary.model.repository

import io.reactivex.Observable
import ru.geekbrains.dictionary.model.datasource.local.DatabaseStorage
import ru.geekbrains.dictionary.model.datasource.remote.ApiService
import ru.geekbrains.dictionary.model.entities.DataModel
import ru.geekbrains.dictionary.model.entities.mapper.TranslationToDataModelMapper
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(
    private val databaseStorage: DatabaseStorage,
    private val apiService: ApiService
) : IRepository{

    override fun getData(word: String): Observable<List<DataModel>> =
        apiService.search(word)

}
