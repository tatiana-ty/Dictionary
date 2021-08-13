package ru.geekbrains.dictionary.model.repository

import ru.geekbrains.dictionary.model.entities.AppState


interface IRepositoryLocal<T> : IRepository<T> {

    suspend fun saveToDB(appState: AppState)
}
