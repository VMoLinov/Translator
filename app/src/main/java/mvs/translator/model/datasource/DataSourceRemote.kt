package mvs.translator.model.datasource

import mvs.translator.model.data.DataModel
import io.reactivex.Observable
import mvs.translator.model.datasource.DataSource
import mvs.translator.model.datasource.RetrofitImplementation

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
