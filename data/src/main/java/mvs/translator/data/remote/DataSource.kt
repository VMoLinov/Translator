package mvs.translator.data.remote

// Источник данных для репозитория
interface DataSource<T> {

    suspend fun getData(word: String): T
}
