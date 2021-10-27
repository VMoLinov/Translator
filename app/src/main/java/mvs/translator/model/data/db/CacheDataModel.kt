package mvs.translator.model.data.db

import mvs.translator.model.data.DataModel

interface CacheDataModel {

    fun fromDataModelToRoom(data: List<DataModel>): List<RoomDataModel>

    fun fromDataBaseToDataModel(text: String): List<DataModel>
}
