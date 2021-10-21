package mvs.translator.data.remote

import io.reactivex.Observable
import mvs.translator.DataModel
import mvs.translator.DataSource

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
) : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return remoteProvider.getData(word)
    }
}
