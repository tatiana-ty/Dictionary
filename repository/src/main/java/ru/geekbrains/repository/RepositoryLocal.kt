package ru.geekbrains.repository

import ru.geekbrains.model.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}
