package mvs.translator.model.datasource

import mvs.translator.model.data.AppState
import mvs.translator.model.data.DataModel
import mvs.translator.room.HistoryDao
import mvs.translator.utils.convertDataModelSuccessToEntity
import mvs.translator.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
