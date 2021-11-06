package mvs.translator.ui.main

import mvs.translator.model.data.AppState
import mvs.translator.model.data.DataModel
import mvs.translator.model.datasource.LocalSource
import mvs.translator.model.repository.Repository
import mvs.translator.viewmodel.Interactor

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: LocalSource<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, isRemoteSource: Boolean): AppState {
        return if (isRemoteSource) {
            val data = remoteRepository.getData(word)
            AppState.Success(data)
        } else {
            val data = localRepository.getData(word)
            AppState.Success(data)
        }
    }

    override suspend fun insertData(data: AppState, word: String) {
        when (data) {
            is AppState.Success -> localRepository.saveData(data.data!!, word)
            else -> {
                TODO()
            }
        }
    }
}
