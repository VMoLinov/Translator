package mvs.translator.view.history

import mvs.translator.data.local.RepositoryLocal
import mvs.translator.data.remote.Repository
import mvs.translator.model.DataModel
import mvs.translator.viewmodel.Interactor

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<mvs.translator.model.AppState> {

    override suspend fun getData(
        word: String,
        isRemoteSource: Boolean
    ): mvs.translator.model.AppState {
        return mvs.translator.model.AppState.Success(
            if (isRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}