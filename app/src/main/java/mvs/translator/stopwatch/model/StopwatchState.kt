package mvs.translator.stopwatch.model

sealed interface StopwatchState {

    data class Paused(
        val elapsedTime: Long
    ) : StopwatchState

    data class Running(
        val startTime: Long,
        val elapsedTime: Long
    ) : StopwatchState
}
