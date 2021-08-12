package ru.geekbrains.dictionary.view.translation

import ru.geekbrains.dictionary.model.entities.AppState
import ru.geekbrains.dictionary.model.entities.DataModel
import ru.geekbrains.dictionary.model.repository.IRepository
import ru.geekbrains.dictionary.model.repository.IRepositoryLocal
import ru.geekbrains.dictionary.viewModel.Interactor

class TranslationInteractor(
    private val repositoryRemote: IRepository<List<DataModel>>,
    private val repositoryLocal: IRepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
