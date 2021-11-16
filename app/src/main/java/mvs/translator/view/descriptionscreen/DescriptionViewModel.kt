package mvs.translator.view.descriptionscreen

import mvs.translator.model.AppState
import mvs.translator.viewmodel.BaseViewModel

class DescriptionViewModel(private val interactor: DescriptionInteractor) :
    BaseViewModel<AppState>() {

    override suspend fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(interactor.getData(word, isOnline))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }
}
