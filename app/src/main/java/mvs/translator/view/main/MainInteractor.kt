package mvs.translator.view.main

import mvs.translator.data.local.RepositoryLocal
import mvs.translator.data.remote.Repository
import mvs.translator.model.DataModel
import mvs.translator.viewmodel.Interactor

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val dataRepositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<mvs.translator.model.AppState> {

    override suspend fun getData(
        word: String,
        isRemoteSource: Boolean
    ): mvs.translator.model.AppState {
        return if (isRemoteSource) {
            val data = remoteRepository.getData(word)
            mvs.translator.model.AppState.Success(data)
        } else {
            val data = dataRepositoryLocal.getData(word)
            mvs.translator.model.AppState.Success(data)
        }
    }

    suspend fun getSimpleWord(word: String): mvs.translator.model.AppState {
        val data = dataRepositoryLocal.getSimpleWord(word)
        return mvs.translator.model.AppState.Simple(data)
    }

    suspend fun insertData(data: mvs.translator.model.AppState) {
        when (data) {
            is mvs.translator.model.AppState.Success -> dataRepositoryLocal.saveToDB(data)
            else -> {
            }
        }
    }
}
