package mvs.translator.data.local

import mvs.translator.utils.convertDataModelSuccessToEntity
import mvs.translator.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: mvs.translator.model.room.HistoryDao) :
    DataSourceLocal<List<mvs.translator.model.DataModel>> {

    override suspend fun getData(word: String): List<mvs.translator.model.DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun getSimpleWord(word: String): mvs.translator.model.DataModel {
        return mapHistoryEntityToSearchResult(historyDao.getDataByWord(word))
    }

    override suspend fun saveToDB(appState: mvs.translator.model.AppState) {
        convertDataModelSuccessToEntity(appState).let {
            historyDao.insertAll(it)
        }
    }
}
