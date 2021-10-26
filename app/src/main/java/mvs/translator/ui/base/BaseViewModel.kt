package mvs.translator.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import mvs.translator.AppState
import mvs.translator.rx.ISchedulerProvider
import mvs.translator.rx.SchedulerProvider

abstract class BaseViewModel<T : AppState>(
    protected val stateLiveData: MutableLiveData<T> = MutableLiveData(),
    protected val schedulerProvider: ISchedulerProvider = SchedulerProvider(),
    protected val savedStateHandle: SavedStateHandle = SavedStateHandle()
) : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    fun getStateLiveData(): LiveData<T> = stateLiveData

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
