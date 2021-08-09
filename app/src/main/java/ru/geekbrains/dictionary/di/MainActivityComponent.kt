package ru.geekbrains.dictionary.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import ru.geekbrains.dictionary.view.main.MainActivity
import ru.geekbrains.dictionary.view.main.MainViewModel

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}

@Module
interface MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindViewModel(viewModel: MainViewModel): ViewModel

}