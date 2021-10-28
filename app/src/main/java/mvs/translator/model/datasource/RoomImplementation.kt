package mvs.translator.model.datasource

import mvs.translator.model.data.DataModel
import mvs.translator.model.data.db.CacheDataModel

class RoomImplementation(private val cache: CacheDataModel) :
    DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return cache.fromDataBaseToDataModel(word)
    }

    override suspend fun insertData(data: List<DataModel>?, word: String) {
        if (data != null) {
            cache.fromDataModelToRoom(data, word)
        }
    }
}
