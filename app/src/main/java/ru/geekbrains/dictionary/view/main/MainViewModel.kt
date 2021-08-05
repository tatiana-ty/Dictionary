package ru.geekbrains.dictionary.view.main

import androidx.lifecycle.LiveData
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import ru.geekbrains.dictionary.model.entities.AppState
import ru.geekbrains.dictionary.model.entities.DataModel
import ru.geekbrains.dictionary.model.repository.Repository
import ru.geekbrains.dictionary.utils.parseSearchResults
import ru.geekbrains.dictionary.viewModel.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            repository.getData(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(doOnSubscribe())
                .subscribe(
                    ::onSuccess,
                    ::onError
                )
        )
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { liveDataForViewToObserve.value = AppState.Loading(null) }

    private fun onSuccess(list: List<DataModel>) {
        appState = AppState.Success(list)
        liveDataForViewToObserve.value = appState
    }

    private fun onError(e: Throwable) {
        liveDataForViewToObserve.value = AppState.Error(e)
    }
}
