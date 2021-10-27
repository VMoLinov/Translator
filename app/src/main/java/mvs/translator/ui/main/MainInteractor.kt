package mvs.translator.ui.main

import mvs.translator.model.data.AppState
import mvs.translator.model.data.DataModel
import mvs.translator.model.repository.Repository
import mvs.translator.viewmodel.Interactor

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, isRemoteSource: Boolean): AppState {
        return if (isRemoteSource) {
            val data = remoteRepository.getData(word)
            localRepository.insertData(data)
            AppState.Success(data)
        } else {
            val data = localRepository.getData(word)
            AppState.Success(data)
        }
    }
}
