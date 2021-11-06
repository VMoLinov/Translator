package mvs.translator.view.history

import mvs.translator.model.data.AppState
import mvs.translator.model.data.DataModel
import mvs.translator.model.repository.Repository
import mvs.translator.model.repository.RepositoryLocal
import mvs.translator.viewmodel.Interactor

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, isRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (isRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}