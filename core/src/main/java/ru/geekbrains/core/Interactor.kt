package ru.geekbrains.core

interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}
