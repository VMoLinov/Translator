package mvs.translator.view.base

import mvs.translator.model.data.AppState

interface View {

    fun renderData(appState: AppState)
}
