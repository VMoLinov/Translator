package mvs.translator.model.repository

// Получение и/или хранение данных для передачи интерактору
interface Repository<T> {

    suspend fun getData(word: String): T
}
