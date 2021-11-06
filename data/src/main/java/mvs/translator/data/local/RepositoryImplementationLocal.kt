package mvs.translator.data.local

import mvs.translator.data.AppState
import mvs.translator.data.DataModel

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getSimpleWord(word: String): DataModel {
        return dataSource.getSimpleWord(word)
    }
}
