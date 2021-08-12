package ru.geekbrains.dictionary.model.datasource

import geekbrains.ru.translator.utils.convertDataModelSuccessToEntity
import geekbrains.ru.translator.utils.mapHistoryEntityToSearchResult
import ru.geekbrains.dictionary.model.datasource.local.DictionaryDao
import ru.geekbrains.dictionary.model.entities.AppState
import ru.geekbrains.dictionary.model.entities.DataModel


class RoomDataBaseImplementation(private val dictionaryDao: DictionaryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(dictionaryDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            dictionaryDao.insert(it)
        }
    }
}
