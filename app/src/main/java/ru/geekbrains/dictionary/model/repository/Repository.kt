package ru.geekbrains.dictionary.model.repository

import ru.geekbrains.dictionary.model.datasource.DataSource
import ru.geekbrains.dictionary.model.entities.DataModel
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataSource: DataSource<List<DataModel>>
) : IRepository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> =
        dataSource.getData(word)

}
