package mvs.translator.model.datasource

import mvs.translator.model.data.DataModel

// Источник данных для репозитория
interface DataSource<T> {

    suspend fun getData(word: String): T

    suspend fun insertData(data: T?, word: String)
}
