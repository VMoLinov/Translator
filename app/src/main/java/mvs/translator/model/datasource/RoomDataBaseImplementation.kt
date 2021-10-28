package mvs.translator.model.datasource

import mvs.translator.model.data.DataModel
import mvs.translator.model.data.db.CacheDataModel

class RoomDataBaseImplementation(private val cache: CacheDataModel) :
    DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return cache.fromDataBaseToDataModel(word)
    }

    override suspend fun insertData(dataModel: List<DataModel>?, word: String) {
        if (dataModel != null) {
            cache.fromDataModelToRoom(dataModel, word)
        }
    }
}
