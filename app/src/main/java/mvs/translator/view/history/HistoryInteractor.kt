package mvs.translator.view.history

import mvs.translator.data.local.RepositoryLocal
import mvs.translator.data.remote.Repository
import mvs.translator.model.AppState
import mvs.translator.model.DataModel
import mvs.translator.viewmodel.Interactor

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(
        word: String,
        isRemoteSource: Boolean
    ): AppState {
        return AppState.Success(
            if (isRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}