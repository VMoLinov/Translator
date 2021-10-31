package mvs.translator.stopwatch.model

import kotlinx.coroutines.flow.StateFlow

interface Interactor {

    val ticker: StateFlow<String>

    fun start()

    fun pause()

    fun stop()
}
