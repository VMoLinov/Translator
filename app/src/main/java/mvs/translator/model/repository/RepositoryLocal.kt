package mvs.translator.model.repository

import mvs.translator.model.data.AppState
import mvs.translator.model.data.DataModel

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)

    suspend fun getSimpleWord(word: String): DataModel
}
