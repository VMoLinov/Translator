package mvs.translator.data.local

import mvs.translator.data.AppState
import mvs.translator.data.DataModel
import mvs.translator.data.remote.Repository

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)

    suspend fun getSimpleWord(word: String): DataModel
}
