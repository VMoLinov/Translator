package mvs.translator.model.datasource

// Источник данных для репозитория
interface RemoteSource<T> {

    suspend fun getData(word: String): T
}
