package mvs.translator.data.local

import mvs.translator.data.AppState
import mvs.translator.data.DataModel
import mvs.translator.utils.convertDataModelSuccessToEntity
import mvs.translator.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun getSimpleWord(word: String): DataModel {
        return mapHistoryEntityToSearchResult(historyDao.getDataByWord(word))
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState).let {
            historyDao.insertAll(it)
        }
    }
}
