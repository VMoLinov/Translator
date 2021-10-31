package mvs.translator.stopwatch.model.timestamp

import mvs.translator.stopwatch.model.timestamp.TimestampProvider
import mvs.translator.stopwatch.model.state.StopwatchState

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider,
) {

    fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else 0
        return timePassedSinceStart + state.elapsedTime
    }
}
