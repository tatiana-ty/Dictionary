package ru.geekbrains.dictionary.view.base

import ru.geekbrains.dictionary.model.entities.AppState

interface View {

    fun renderData(appState: AppState)

}
