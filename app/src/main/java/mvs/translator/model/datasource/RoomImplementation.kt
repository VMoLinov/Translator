package mvs.translator.model.datasource

import mvs.translator.model.data.DataModel
import mvs.translator.model.data.db.CacheDataModel

class RoomImplementation(private val cache: CacheDataModel) :
    LocalSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return cache.fromDataBaseToDataModel(word)
    }

    override suspend fun saveData(data: List<DataModel>, word: String) {
        cache.fromDataModelToRoom(data, word)
    }
}
