package kr.co.knowledgerally.log

object Logger {
    private val loggers = mutableSetOf<Loggable>()

    fun add(loggable: Loggable) {
        loggers.add(loggable)
    }

    fun v(tag: String, msg: Any) = log(Level.VERBOSE, tag, msg)
    fun d(tag: String, msg: Any) = log(Level.DEBUG, tag, msg)
    fun e(tag: String, msg: Any) = log(Level.ERROR, tag, msg)
    fun w(tag: String, msg: Any) = log(Level.WARNING, tag, msg)
    fun i(tag: String, msg: Any) = log(Level.INFO, tag, msg)

    private fun log(level: Level, tag: String, msg: Any) = loggers.forEach { logger ->
        when (level) {
            Level.VERBOSE -> logger.v(tag, msg.toString())
            Level.DEBUG -> logger.d(tag, msg.toString())
            Level.INFO -> logger.i(tag, msg.toString())
            Level.WARNING -> logger.w(tag, msg.toString())
            Level.ERROR -> if (msg is Throwable) {
                logger.e(tag, msg)
            } else {
                logger.e(tag, msg.toString())
            }
        }
    }

    enum class Level {
        VERBOSE,
        DEBUG,
        INFO,
        WARNING,
        ERROR,
    }
}
