package mvs.translator.stopwatch.model

interface Interactor {

    fun start()

    fun pause()

    fun stop()

    fun isActive(): Boolean

    fun getValue(): String
}
