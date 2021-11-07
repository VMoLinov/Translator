package mvs.translator.data.local

import mvs.translator.model.AppState
import mvs.translator.model.DataModel

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<mvs.translator.model.DataModel>>) :
    RepositoryLocal<List<mvs.translator.model.DataModel>> {

    override suspend fun getData(word: String): List<mvs.translator.model.DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: mvs.translator.model.AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getSimpleWord(word: String): mvs.translator.model.DataModel {
        return dataSource.getSimpleWord(word)
    }
}
