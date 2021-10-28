package mvs.translator.viewmodel

import mvs.translator.model.data.DataModel

// Слой бизнес-логики
interface Interactor<T> {

    suspend fun getData(word: String, isRemoteSource: Boolean): T

    suspend fun insertData(data: T, word: String)
}
