package mvs.translator.model.datasource

import mvs.translator.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
