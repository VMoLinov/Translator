package mvs.translator.model.data.db

import mvs.translator.model.data.DataModel

interface CacheDataModel {

    fun fromDataModelToRoom(data: List<DataModel>, word: String)

    fun fromDataBaseToDataModel(word: String): List<DataModel>
}
