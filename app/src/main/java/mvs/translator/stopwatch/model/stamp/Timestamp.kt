package mvs.translator.stopwatch.model.stamp

object Timestamp : TimestampProvider {

    override fun getMilliseconds(): Long {
        return System.currentTimeMillis()
    }
}
