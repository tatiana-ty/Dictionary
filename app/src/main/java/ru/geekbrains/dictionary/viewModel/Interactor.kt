package ru.geekbrains.dictionary.viewModel

interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}
