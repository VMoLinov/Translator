package mvs.translator.stopwatch.model.timestamp

object Timestamp : TimestampProvider {

    override fun getMilliseconds(): Long {
        return System.currentTimeMillis()
    }
}
