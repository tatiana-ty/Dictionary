package ru.geekbrains.dictionary.model.datasource.remote

import ru.geekbrains.dictionary.model.entities.DataModel
import ru.geekbrains.dictionary.model.datasource.DataSource
import io.reactivex.Observable

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
