package mvs.translator.model.data.remote

import io.reactivex.Observable
import mvs.translator.DataSource
import mvs.translator.model.data.DataModel
import mvs.translator.model.datasource.RetrofitImplementation

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
) : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return remoteProvider.getData(word)
    }
}
