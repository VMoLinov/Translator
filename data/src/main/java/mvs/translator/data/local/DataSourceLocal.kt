package mvs.translator.data.local

import mvs.translator.data.AppState
import mvs.translator.data.DataModel
import mvs.translator.data.remote.DataSource

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)

    suspend fun getSimpleWord(word: String): DataModel
}
