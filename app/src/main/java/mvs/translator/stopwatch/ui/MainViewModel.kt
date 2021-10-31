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
    val liveData: MutableLiveData<MutableList<String>> = MutableLiveData(
        mutableListOf(
            DEFAULT_VALUE,
            DEFAULT_VALUE
        )
    ),
    private val timestamp: TimestampProvider = Timestamp,
    private val mainViewScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
) : ViewModel() {

    private var jobList: MutableList<Job> = mutableListOf()
    private val interactorList: MutableList<Interactor> = mutableListOf()
    private var index = -1

    private fun startJob() {
        interactorList[++index].start()
        jobList.add(mainViewScope.launch {
            while (isActive) {
                liveData.value?.set(index, interactorList[index].getValue())
                liveData.value = liveData.value
                delay(15)
            }
        })
    }

    private fun initInteractor() {
        interactorList.add(
            StopwatchStateHolder(
                StopwatchStateCalculator(timestamp, ElapsedTimeCalculator(timestamp)),
                ElapsedTimeCalculator(timestamp), TimestampMillisecondsFormatter()
            )
        )
    }

    fun start() {
        if (jobList.size < TIMERS) {
            initInteractor()
            startJob()
        }
    }

    fun pause() {
        if (jobList.size != 0) {
            interactorList[index].pause()
            stopJob()
            index--
        }
    }

    fun stop() {
        if (jobList.size != 0) {
            interactorList[index].stop()
            stopJob()
            clearValue()
        }
    }

    private fun stopJob() {
        jobList[index].cancel()
        jobList.removeAt(index)
    }

    private fun clearValue() {
        liveData.value?.set(index--, DEFAULT_VALUE)
    }

    companion object {
        const val TIMERS = 2
        const val DEFAULT_VALUE = "00:00:000"
    }
}
