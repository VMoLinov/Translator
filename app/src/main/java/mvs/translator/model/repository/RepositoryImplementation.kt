package mvs.translator.model.repository

import mvs.translator.DataSource
import mvs.translator.model.data.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
