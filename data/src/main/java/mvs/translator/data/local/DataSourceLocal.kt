package mvs.translator.data.local

import mvs.translator.data.remote.DataSource

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: mvs.translator.model.AppState)

    suspend fun getSimpleWord(word: String): mvs.translator.model.DataModel
}
