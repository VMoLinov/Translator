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
                roomMeaning.add(RoomMeaning(it.imageUrl, dataModel.text, word))
                roomTranslation.add(
                    RoomTranslation(
                        it.translation?.translation ?: "",
                        dataModel.text,
                        word
                    )
                )
            }
        }
        database.dbDao.insertData(roomDataModel, roomMeaning, roomTranslation)
    }


    override fun fromDataBaseToDataModel(word: String): List<DataModel> {
        val roomDataModel = database.dbDao.getDataModelByWord(word)
        val roomMeaning = database.dbDao.getMeaningByWord(word)
        val roomTranslation = database.dbDao.getTranslationByWord(word).toMutableList()
        val dataModelList: MutableList<DataModel> = mutableListOf()
        val meaningList: MutableList<Meaning> = mutableListOf()
        roomDataModel.forEach { dataModel ->
            val text = dataModel.text
            roomMeaning.forEach mean@{ meaning ->
                if (meaning.parentText == text) {
                    roomTranslation.forEach {
                        if (it.parentText == text) {
                            meaningList.add(
                                Meaning(
                                    Translation(it.translation),
                                    meaning.imageUrl
                                )
                            )
                            roomTranslation.remove(it)
                            return@mean
                        }
                    }
                }
            }
            dataModelList.add(DataModel(text, meaningList.toList()))
            meaningList.clear()
        }
        return dataModelList
    }
}
