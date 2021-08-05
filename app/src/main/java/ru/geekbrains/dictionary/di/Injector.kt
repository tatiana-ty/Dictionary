package ru.geekbrains.dictionary.di

import android.content.Context
import ru.geekbrains.dictionary.di.modules.AppModule
import ru.geekbrains.dictionary.di.modules.RestModule

object Injector {

    private lateinit var appComponent: AppComponent

    val mainActivityComponent: MainActivityComponent
        get() {
            return appComponent.mainActivityComponent
        }


    internal fun initAppComponent(context: Context) {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .restModule(RestModule())
            .build()
    }
}