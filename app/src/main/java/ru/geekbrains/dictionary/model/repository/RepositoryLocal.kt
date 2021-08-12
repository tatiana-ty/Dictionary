package ru.geekbrains.dictionary.model.repository

import ru.geekbrains.dictionary.model.datasource.DataSourceLocal
import ru.geekbrains.dictionary.model.entities.AppState
import ru.geekbrains.dictionary.model.entities.DataModel

class RepositoryLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    IRepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}
