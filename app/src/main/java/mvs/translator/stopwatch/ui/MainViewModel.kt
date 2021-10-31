package mvs.translator.stopwatch.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import mvs.translator.stopwatch.model.*
import mvs.translator.stopwatch.model.formatter.TimestampMillisecondsFormatter
import mvs.translator.stopwatch.model.stamp.Timestamp
import mvs.translator.stopwatch.model.stamp.TimestampProvider

class MainViewModel(
    val liveData: MutableLiveData<String> = MutableLiveData(),
    timestamp: TimestampProvider = Timestamp,
    mainViewScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
) : ViewModel() {

    private val interactor: Interactor = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestamp,
                ElapsedTimeCalculator(timestamp)
            ),
            ElapsedTimeCalculator(timestamp),
            TimestampMillisecondsFormatter()
        ),
        mainViewScope
    )

    fun start() {
        interactor.start()
        liveData.value = interactor.ticker.value
    }

    fun pause() {
        interactor.pause()
        liveData.value = interactor.ticker.value
    }

    fun stop() {
        interactor.stop()
        liveData.value = interactor.ticker.value
    }
}
