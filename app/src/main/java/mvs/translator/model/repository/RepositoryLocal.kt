package mvs.translator.model.repository

import mvs.translator.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}
