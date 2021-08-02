package ru.geekbrains.dictionary.presenter

import ru.geekbrains.dictionary.view.base.View
import ru.geekbrains.dictionary.model.entities.AppState

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
