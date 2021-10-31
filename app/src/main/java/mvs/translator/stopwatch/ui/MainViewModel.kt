package mvs.translator.stopwatch.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import mvs.translator.stopwatch.model.Interactor
import mvs.translator.stopwatch.model.StopwatchStateHolder
import mvs.translator.stopwatch.model.formatter.TimestampMillisecondsFormatter
import mvs.translator.stopwatch.model.state.StopwatchStateCalculator
import mvs.translator.stopwatch.model.timestamp.ElapsedTimeCalculator
import mvs.translator.stopwatch.model.timestamp.Timestamp
import mvs.translator.stopwatch.model.timestamp.TimestampProvider

class MainViewModel(
    val liveData: MutableLiveData<String> = MutableLiveData(),
    timestamp: TimestampProvider = Timestamp,
    private val mainViewScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
) : ViewModel() {

    private var job: Job? = null
    private val interactor: Interactor = StopwatchStateHolder(
        StopwatchStateCalculator(timestamp, ElapsedTimeCalculator(timestamp)),
        ElapsedTimeCalculator(timestamp), TimestampMillisecondsFormatter()
    )

    private fun startJob() {
        interactor.start()
        mainViewScope.launch {
            while (isActive) {
                liveData.value = interactor.getValue()
                delay(15)
            }
        }
    }

    fun start() {
        if (job == null) startJob()
    }

    fun pause() {
        interactor.pause()
        stopJob()
    }

    fun stop() {
        interactor.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob() {
        mainViewScope.coroutineContext.cancelChildren()
        job = null
    }

    fun clearValue() {
        liveData.value = "00:00:000"
    }
}
