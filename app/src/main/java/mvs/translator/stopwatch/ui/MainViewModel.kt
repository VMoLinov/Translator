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
    val liveData: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf()),
    private val mainViewScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
) : ViewModel() {

    private var jobList: MutableList<Job> = mutableListOf()
    private val interactorList: MutableList<Interactor> = mutableListOf()

    init {
        for (i in 1..TIMERS) liveData.value?.add(DEFAULT_VALUE)
    }

    private fun startJob() {
        jobList.add(mainViewScope.launch {
            val index = jobList.lastIndex
            interactorList[index].start()
            while (isActive) {
                liveData.value?.set(index, interactorList[index].getValue())
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
        val index = jobList.lastIndex
        if (jobList.size != 0 && !interactorList[index].isActive()) {
            interactorList[index].start()
        } else if (jobList.size < TIMERS) {
            initInteractor()
            startJob()
        } else {
            interactorList[index].start()
        }
    }

    fun pause() {
        if (jobList.size != 0) {
            val index = jobList.lastIndex
            interactorList[index].pause()
        }
    }

    fun stop() {
        if (jobList.size != 0) {
            val index = jobList.lastIndex
            interactorList[index].stop()
            jobList[index].cancel()
            interactorList.removeAt(index)
            jobList.removeAt(index)
            clearValue(index)
        }
    }

    private fun clearValue(index: Int) {
        liveData.value?.set(index, DEFAULT_VALUE)
        liveData.value = liveData.value
    }

    companion object {
        const val TIMERS = 2
        const val DEFAULT_VALUE = "00:00:000"
    }
}
