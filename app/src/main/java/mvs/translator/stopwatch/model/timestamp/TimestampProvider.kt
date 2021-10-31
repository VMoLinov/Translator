package mvs.translator.stopwatch.model.timestamp

interface TimestampProvider {
    fun getMilliseconds(): Long
}
