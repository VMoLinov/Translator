package mvs.translator.model.data.db

import mvs.translator.model.data.DataModel
import mvs.translator.model.data.Meaning
import mvs.translator.model.data.Translation

class RoomCache(
    private val database: DatabaseModel
) : CacheDataModel {
    override fun fromDataModelToRoom(data: List<DataModel>): List<RoomDataModel> {
        val roomDataModel = data.map { dataModel ->
            RoomDataModel(dataModel.text,
                dataModel.meaning?.map { meaning ->
                    RoomMeaning(
                        RoomTranslation(meaning.translation?.translation, dataModel.text),
                        meaning.imageUrl,
                        dataModel.text
                    )
                })
        }
        database.dbDao.insert(roomDataModel)
        return roomDataModel
    }

    override fun fromDataBaseToDataModel(text: String): List<DataModel> {
        val roomDataModel = database.dbDao.getDataModelByParentText(text)
        val dataModel = roomDataModel.map { dataModel ->
            DataModel(dataModel.text,
                dataModel.meaning?.map { meaning ->
                    Meaning(
                        Translation(meaning.translation?.translation),
                        meaning.imageUrl
                    )
                })
        }
        return dataModel
    }
}
