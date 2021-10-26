package mvs.translator.ui.main

import io.reactivex.observers.DisposableObserver
import mvs.translator.AppState
import mvs.translator.DataModel
import mvs.translator.interactor.main.MainInteractor
import mvs.translator.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainInteractor,
) : BaseViewModel<AppState>() {

    fun getWordDescriptions(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io)
                .observeOn(schedulerProvider.ui)
                .doOnSubscribe { stateLiveData.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver() = object : DisposableObserver<AppState>() {
        override fun onNext(appState: AppState) {
            stateLiveData.value = appState
        }

        override fun onError(e: Throwable) {
            stateLiveData.value = AppState.Error(e)
        }

        override fun onComplete() = Unit
    }

    fun saveState(data: List<DataModel>) {
        savedStateHandle.set(KEY, data)
    }

    fun getState(): AppState? {
        val state = savedStateHandle.get<List<DataModel>>(KEY)
        return if (state != null) AppState.Success(state)
        else null
    }

    companion object {
        private const val KEY = "key"
    }
}
