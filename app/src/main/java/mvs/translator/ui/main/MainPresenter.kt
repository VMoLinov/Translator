package mvs.translator.ui.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import mvs.translator.AppState
import mvs.translator.data.RepoImpl
import mvs.translator.data.remote.DataSourceRemote
import mvs.translator.interactor.main.MainInteractor

class MainPresenter(
    private val interactor: MainInteractor = MainInteractor(
        RepoImpl(DataSourceRemote()),
        RepoImpl(DataSourceRemote())
    )
) : MvpPresenter<MainView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun detachView(view: MainView?) {
        super.detachView(view)
        compositeDisposable.clear()
    }

    fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(interactor.getData(word, isOnline)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.renderData(AppState.Loading(null)) }
            .subscribe { viewState.renderData(it) }
        )
    }
}
