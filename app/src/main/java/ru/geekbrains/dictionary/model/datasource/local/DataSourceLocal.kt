package ru.geekbrains.dictionary.model.datasource.local

import ru.geekbrains.dictionary.model.entities.DataModel
import ru.geekbrains.dictionary.model.datasource.DataSource
import io.reactivex.Observable

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
