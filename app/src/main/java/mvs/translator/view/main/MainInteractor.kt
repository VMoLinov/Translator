package mvs.translator.view.main

import mvs.translator.model.data.AppState
import mvs.translator.model.data.DataModel
import mvs.translator.model.repository.Repository
import mvs.translator.model.repository.RepositoryLocal
import mvs.translator.viewmodel.Interactor

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val dataRepositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, isRemoteSource: Boolean): AppState {
        return if (isRemoteSource) {
            val data = remoteRepository.getData(word)
            AppState.Success(data)
        } else {
            val data = dataRepositoryLocal.getData(word)
            AppState.Success(data)
        }
    }

    suspend fun getSimpleWord(word: String) : AppState{
        val data = dataRepositoryLocal.getSimpleWord(word)
        return AppState.Success(listOf(data))
    }

    suspend fun insertData(data: AppState) {
        when (data) {
            is AppState.Success -> dataRepositoryLocal.saveToDB(data)
            else -> {
            }
        }
    }
}
