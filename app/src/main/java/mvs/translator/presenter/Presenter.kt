package mvs.translator.presenter

import mvs.translator.model.data.AppState
import mvs.translator.view.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
