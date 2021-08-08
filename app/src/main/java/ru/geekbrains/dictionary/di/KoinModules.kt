package ru.geekbrains.dictionary.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.geekbrains.dictionary.model.datasource.RetrofitImplementation
import ru.geekbrains.dictionary.model.datasource.RoomDataBaseImplementation
import ru.geekbrains.dictionary.model.datasource.local.DatabaseStorage
import ru.geekbrains.dictionary.model.datasource.remote.ApiService
import ru.geekbrains.dictionary.model.entities.DataModel
import ru.geekbrains.dictionary.model.repository.IRepository
import ru.geekbrains.dictionary.model.repository.Repository
import ru.geekbrains.dictionary.view.main.MainInteractor
import ru.geekbrains.dictionary.view.main.MainViewModel

val application = module {
    single<IRepository<List<DataModel>>>(named(NAME_REMOTE)) { Repository(RetrofitImplementation()) }
    single<IRepository<List<DataModel>>>(named(NAME_LOCAL)) { Repository(RoomDataBaseImplementation()) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
