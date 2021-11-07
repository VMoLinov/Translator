package mvs.translator.view.history

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import mvs.translator.model.AppState
import mvs.translator.viewmodel.BaseViewModel

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<mvs.translator.model.AppState>() {

    private val liveDataForViewToObserve: LiveData<mvs.translator.model.AppState> = _mutableLiveData

    fun subscribe(): LiveData<mvs.translator.model.AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = mvs.translator.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(
            mvs.translator.utils.parseLocalSearchResults(
                interactor.getData(
                    word,
                    isOnline
                )
            )
        )
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(mvs.translator.model.AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = mvs.translator.model.AppState.Success(null)//Set View to original state in onStop
        super.onCleared()
    }
}
