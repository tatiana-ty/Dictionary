package ru.geekbrains.repository

import ru.geekbrains.model.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
