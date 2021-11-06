package mvs.translator.model.datasource

import mvs.translator.model.data.AppState
import mvs.translator.model.data.DataModel

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)

    suspend fun getSimpleWord(word: String): DataModel
}
