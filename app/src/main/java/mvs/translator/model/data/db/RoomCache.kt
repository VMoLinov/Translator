package mvs.translator.model.data.db

import mvs.translator.model.data.DataModel
import mvs.translator.model.data.Meaning
import mvs.translator.model.data.Translation

class RoomCache(
    private val database: DatabaseModel
) : CacheDataModel {
    override fun fromDataModelToRoom(data: List<DataModel>, word: String) {
        val roomDataModel = data.map { RoomDataModel(it.text, word) }
        val roomMeaning: MutableList<RoomMeaning> = mutableListOf()
        val roomTranslation: MutableList<RoomTranslation> = mutableListOf()
        data.forEach { dataModel ->
            dataModel.meaning?.map {
                roomMeaning.add(RoomMeaning(it.imageUrl, dataModel.text))
                roomTranslation.add(RoomTranslation(it.translation!!.translation, dataModel.text))
            }
        }
        database.dbDao.insertData(roomDataModel, roomMeaning, roomTranslation)
    }

    override fun fromDataBaseToDataModel(word: String): List<DataModel> {
        val roomDataModel = database.dbDao.getDataModelByParentText(text)
        val roomMeaning = database.dbDao.getMeaningByParentText(text)
        val roomTranslation = database.dbDao.getTranslationByParentText(text)
        val meaningList: MutableList<Meaning> = mutableListOf()
        roomTranslation.map { translation ->
            roomMeaning.map { meaning ->
                meaningList.add(Meaning(Translation(translation.translation), meaning.imageUrl))
            }
        }
        val dataModelList: MutableList<DataModel> = mutableListOf()
        roomDataModel.map { dataModel ->
            dataModelList.add(DataModel(dataModel.text, meaningList))
        }
        return dataModelList
    }
}
