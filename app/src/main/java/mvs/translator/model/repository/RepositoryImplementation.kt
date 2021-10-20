package mvs.translator.model.repository

import mvs.translator.model.data.DataModel
import mvs.translator.model.datasource.DataSource
import io.reactivex.Observable
import mvs.translator.model.repository.Repository

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
