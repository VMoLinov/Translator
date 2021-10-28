package mvs.translator.model.repository

import mvs.translator.model.data.DataModel

// Получение и/или хранение данных для передачи интерактору
interface Repository<T> {

    suspend fun getData(word: String): T
}
