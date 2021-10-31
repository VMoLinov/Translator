package mvs.translator.stopwatch.model.stamp

interface TimestampProvider {
    fun getMilliseconds(): Long
}
