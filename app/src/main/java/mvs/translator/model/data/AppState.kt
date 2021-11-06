package mvs.translator.model.data

sealed interface AppState {
    data class Success(val data: List<DataModel>?) : AppState
    data class Simple(val data: DataModel?) : AppState
    data class Error(val error: Throwable) : AppState
    data class Loading(val progress: Int? = null) : AppState
}
