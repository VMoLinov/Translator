package mvs.translator

import io.reactivex.Observable

sealed interface AppState {
    data class Success(val data: List<DataModel>) : AppState
    data class Error(val t: Throwable) : AppState
    data class Loading(val progress: Int? = null) : AppState
}

interface View {

    fun renderData(appState: AppState)
}

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}

// Слой бизнес-логики
interface Interactor<T> {

    fun getData(word: String, isRemoteSource: Boolean): Observable<T>
}

// Получение и/или хранение данных для передачи интерактору
interface Repository<T> {

    fun getData(word: String): Observable<T>
}

// Источник данных для репозитория
interface DataSource<T> {

    fun getData(word: String): Observable<T>
}
