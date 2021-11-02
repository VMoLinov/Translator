package mvs.translator.stopwatch.model

import mvs.translator.stopwatch.model.formatter.TimestampMillisecondsFormatter
import mvs.translator.stopwatch.model.state.StopwatchState
import mvs.translator.stopwatch.model.state.StopwatchStateCalculator
import mvs.translator.stopwatch.model.timestamp.ElapsedTimeCalculator

class StopwatchStateHolder(
    private val stopwatchStateCalculator: StopwatchStateCalculator,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timestampMillisecondsFormatter: TimestampMillisecondsFormatter
) : Interactor {

    var currentState: StopwatchState = StopwatchState.Paused(0)
        private set

    override fun start() {
        currentState = stopwatchStateCalculator.calculateRunningState(currentState)
    }

    override fun pause() {
        currentState = stopwatchStateCalculator.calculatePausedState(currentState)
    }

    override fun stop() {
        currentState = StopwatchState.Paused(0)
    }

    override fun isActive(): Boolean = currentState is StopwatchState.Running

    override fun getValue() = getStringTimeRepresentation()

    private fun getStringTimeRepresentation(): String {
        val elapsedTime = when (val currentState = currentState) {
            is StopwatchState.Paused -> currentState.elapsedTime
            is StopwatchState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timestampMillisecondsFormatter.format(elapsedTime)
    }
}
