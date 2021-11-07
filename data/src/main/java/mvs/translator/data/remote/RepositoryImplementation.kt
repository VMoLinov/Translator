package mvs.translator.data.remote

import mvs.translator.model.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<mvs.translator.model.DataModel>>) :
    Repository<List<mvs.translator.model.DataModel>> {

    override suspend fun getData(word: String): List<mvs.translator.model.DataModel> {
        return dataSource.getData(word)
    }
}
