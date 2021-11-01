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
    private val mainViewScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
) : ViewModel() {

    private var jobList: MutableList<Job> = mutableListOf()
    private val interactorList: MutableList<Interactor> = mutableListOf()
    private var index = -1

    private fun startJob() {
        jobList.add(mainViewScope.launch {
            val timer = ++index
            interactorList[timer].start()
            while (isActive) {
                liveData.value?.set(timer, interactorList[timer].getValue())
                liveData.value = liveData.value
                delay(15)
            }
        })
    }

    private fun initInteractor() {
        val timestamp: TimestampProvider = Timestamp
        interactorList.add(
            StopwatchStateHolder(
                StopwatchStateCalculator(timestamp, ElapsedTimeCalculator(timestamp)),
                ElapsedTimeCalculator(timestamp), TimestampMillisecondsFormatter()
            )
        )
    }

    fun start() {
        when {
            interactorList.size < TIMERS -> {
                initInteractor()
                startJob()
            }
            jobList.size < TIMERS -> startJob()
        }
    }

    fun pause() {
        if (jobList.size != 0) {
            interactorList[index].pause()
            stopJob()
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
        liveData.value = liveData.value
    }

    companion object {
        const val TIMERS = 2
        const val DEFAULT_VALUE = "00:00:000"
    }
}
