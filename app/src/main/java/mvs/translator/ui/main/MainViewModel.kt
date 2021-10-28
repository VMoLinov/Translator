package mvs.translator.ui.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mvs.translator.model.data.AppState
import mvs.translator.viewmodel.BaseViewModel

class MainViewModel(
    private val interactor: MainInteractor,
) : BaseViewModel<AppState>() {

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            val data = interactor.getData(word, isOnline)
            _mutableLiveData.value = data
            withContext(Dispatchers.IO) {
                interactor.insertData(data,word)
            }
        }
    }

//    Doesn't have to use withContext for Retrofit call if you use .addCallAdapterFactory(CoroutineCallAdapterFactory()). The same goes for Room
//    private suspend fun startInteractor(word: String, isOnline: Boolean) =
//        withContext(Dispatchers.IO) {
//            _mutableLiveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
//        }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}
