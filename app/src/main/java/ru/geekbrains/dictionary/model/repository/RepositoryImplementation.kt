package ru.geekbrains.dictionary.model.repository

import io.reactivex.Observable
import ru.geekbrains.dictionary.model.datasource.DataSource
import ru.geekbrains.dictionary.model.entities.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
