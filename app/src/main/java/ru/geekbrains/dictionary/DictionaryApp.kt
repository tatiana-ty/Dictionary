package ru.geekbrains.dictionary

import android.app.Application
import ru.geekbrains.dictionary.di.Injector

class DictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Injector.initAppComponent(this)
    }
}