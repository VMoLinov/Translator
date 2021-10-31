package mvs.translator.model.repository

import mvs.translator.model.data.DataModel
import mvs.translator.model.datasource.RemoteSource

class RepositoryImplementation(private val remoteSource: RemoteSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return remoteSource.getData(word)
    }
}
