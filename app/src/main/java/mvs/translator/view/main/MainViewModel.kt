package mvs.translator.view.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mvs.translator.model.AppState
import mvs.translator.viewmodel.BaseViewModel

class MainViewModel(
    private val interactor: MainInteractor,
) : BaseViewModel<mvs.translator.model.AppState>() {

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = mvs.translator.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    fun getSimpleWord(word: String) {
        _mutableLiveData.value = mvs.translator.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            val appState = interactor.getSimpleWord(word)
            _mutableLiveData.postValue(appState)
        }
    }

    //Doesn't have to use withContext for Retrofit call if you use .addCallAdapterFactory(CoroutineCallAdapterFactory()). The same goes for Room
    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            val appState =
                mvs.translator.utils.parseOnlineSearchResults(interactor.getData(word, isOnline))
            _mutableLiveData.postValue(appState)
            if (isOnline) interactor.insertData(appState)
        }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(mvs.translator.model.AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = mvs.translator.model.AppState.Success(null)
        super.onCleared()
    }
}
