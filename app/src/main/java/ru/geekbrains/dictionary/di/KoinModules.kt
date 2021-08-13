package ru.geekbrains.dictionary.di

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.geekbrains.dictionary.model.datasource.RetrofitImplementation
import ru.geekbrains.dictionary.model.datasource.RoomDataBaseImplementation
import ru.geekbrains.dictionary.model.datasource.local.DatabaseStorage
import ru.geekbrains.dictionary.model.datasource.remote.ApiService
import ru.geekbrains.dictionary.model.entities.DataModel
import ru.geekbrains.dictionary.model.repository.IRepository
import ru.geekbrains.dictionary.model.repository.IRepositoryLocal
import ru.geekbrains.dictionary.model.repository.Repository
import ru.geekbrains.dictionary.model.repository.RepositoryLocal
import ru.geekbrains.dictionary.view.main.MainInteractor
import ru.geekbrains.dictionary.view.main.MainViewModel
import ru.geekbrains.dictionary.view.translation.TranslationInteractor
import ru.geekbrains.dictionary.view.translation.TranslationViewModel

val application = module {
    single { Room.databaseBuilder(get(), DatabaseStorage::class.java, "HistoryDB").build() }
    single { get<DatabaseStorage>().dictionaryDao }
    single<IRepository<List<DataModel>>> { Repository(RetrofitImplementation()) }
    single<IRepositoryLocal<List<DataModel>>> { RepositoryLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}

val translationScreen = module {
    factory { TranslationViewModel(get()) }
    factory { TranslationInteractor(get(), get()) }
}
