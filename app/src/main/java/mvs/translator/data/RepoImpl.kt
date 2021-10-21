package mvs.translator.data

import io.reactivex.Observable
import mvs.translator.DataModel
import mvs.translator.DataSource
import mvs.translator.Repository

class RepoImpl(
    private val dataSource: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
