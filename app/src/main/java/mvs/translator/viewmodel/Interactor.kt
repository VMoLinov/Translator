package mvs.translator.viewmodel

// Слой бизнес-логики
interface Interactor<T> {

    fun getData(word: String, isRemoteSource: Boolean): T
}
