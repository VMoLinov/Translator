package mvs.translator.view.descriptionscreen

import mvs.translator.data.local.RepositoryLocal
import mvs.translator.data.remote.Repository
import mvs.translator.model.AppState
import mvs.translator.model.DataModel
import mvs.translator.viewmodel.Interactor

class DescriptionInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, isRemoteSource: Boolean): AppState {
        val appState = getSimpleWord(word)
        return if (appState.data?.text != null) {
            appState
        } else {
            AppState.Error(IllegalArgumentException("appState is null"))
        }
    }

    private suspend fun getSimpleWord(word: String): AppState.Simple {
        val data = repositoryLocal.getSimpleWord(word)
        return AppState.Simple(data)
    }
}