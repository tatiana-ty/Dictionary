package ru.geekbrains.repository

interface DataSource<T> {

    suspend fun getData(word: String): T
}
