package mvs.translator.view.main

import mvs.translator.data.local.RepositoryLocal
import mvs.translator.data.remote.Repository
import mvs.translator.model.AppState
import mvs.translator.model.DataModel
import mvs.translator.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(
        word: String,
        isRemoteSource: Boolean
    ): AppState {
        return if (isRemoteSource) {
            val data = repositoryRemote.getData(word)
            AppState.Success(data)
        } else {
            val data = repositoryLocal.getData(word)
            AppState.Success(data)
        }
    }

    suspend fun getSimpleWord(word: String): AppState {
        val data = repositoryLocal.getSimpleWord(word)
        return AppState.Simple(data)
    }

    suspend fun insertData(data: AppState) {
        when (data) {
            is AppState.Success -> repositoryLocal.saveToDB(data)
            else -> {
            }
        }
    }
}
