package ru.geekbrains.dictionary.di

import android.content.Context
import dagger.Component
import dagger.android.AndroidInjector
import ru.geekbrains.dictionary.di.modules.AppModule
import ru.geekbrains.dictionary.di.modules.RestModule
import ru.geekbrains.dictionary.di.modules.ViewModelModule
import ru.geekbrains.dictionary.view.main.MainActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    RestModule::class,
    ViewModelModule::class
])
interface AppComponent {

    val context: Context

    val mainActivityComponent: MainActivityComponent

}