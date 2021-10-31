package mvs.translator.model.datasource

interface LocalSource<T> {

    suspend fun getData(word: String): T

    suspend fun saveData(data: T, word: String)
}
