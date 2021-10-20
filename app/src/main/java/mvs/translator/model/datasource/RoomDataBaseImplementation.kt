package mvs.translator.model.datasource

import mvs.translator.model.data.DataModel
import io.reactivex.Observable
import mvs.translator.model.datasource.DataSource

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
