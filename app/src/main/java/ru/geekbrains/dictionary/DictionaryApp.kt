package ru.geekbrains.dictionary

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.geekbrains.dictionary.di.application
import ru.geekbrains.dictionary.di.mainScreen
import ru.geekbrains.dictionary.di.translationScreen

class DictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, translationScreen))
        }
    }
}