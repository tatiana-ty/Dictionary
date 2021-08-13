package ru.geekbrains.dictionary.model.datasource

import ru.geekbrains.dictionary.model.entities.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
