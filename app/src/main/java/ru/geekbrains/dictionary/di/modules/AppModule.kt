package ru.geekbrains.dictionary.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.geekbrains.dictionary.model.datasource.local.DatabaseStorage
import ru.geekbrains.dictionary.model.repository.IRepository
import ru.geekbrains.dictionary.model.repository.Repository
import javax.inject.Singleton

@Module(includes = [AppModule.InnerAppModule::class])
class AppModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideDatabaseStorage() =
        Room.databaseBuilder(
            context,
            DatabaseStorage::class.java,
            DatabaseStorage.DICTIONARY_DATABASE
        ).build()

    @Module
    interface InnerAppModule {

        @Binds
        @Singleton
        fun provideRepository(repository: Repository): IRepository

    }
}