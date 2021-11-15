package mvs.translator.data.local

import mvs.translator.data.remote.Repository
import mvs.translator.model.AppState
import mvs.translator.model.DataModel

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)

    suspend fun getSimpleWord(word: String): DataModel
}
