package mvs.translator.data.remote

// Получение и/или хранение данных для передачи интерактору
interface Repository<T> {

    suspend fun getData(word: String): T
}
