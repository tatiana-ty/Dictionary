package ru.geekbrains.dictionary.model.repository

import io.reactivex.Observable
import ru.geekbrains.dictionary.model.entities.DataModel

interface IRepository {

    fun getData(word: String): Observable<List<DataModel>>
}
