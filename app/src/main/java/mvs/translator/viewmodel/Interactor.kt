package mvs.translator.viewmodel

// Слой бизнес-логики
interface Interactor<T> {

    suspend fun getData(word: String, isRemoteSource: Boolean): T
}
