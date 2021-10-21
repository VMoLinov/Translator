package mvs.translator.ui.main

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import mvs.translator.AppState

@AddToEndSingle
interface MainView : MvpView {

    fun renderData(appState: AppState)
}
