package com.muffls.monitoring

/**
 * An API for collecting information about crashes combining it into crash reports.
 *
 * [Log] only sends a crash report when a crash occurs. It differentiates between two types of __crashes__:
 * fatal and non-fatal. A __fatal crash__ is when the application crashes. A __non-fatal crash__ is sent when you
 * call [exception]. You can use [e], [w], [i], [d] or [setValue]
 * to add information to each crash report. It sends a maximum of 64 key/value pairs and a crash report
 * can have a maximum of 64 KB, earlier logged
 * information is dropped.
 *
 * Before logging messages you have to initialize the [Log] with [init]. The implementation of a [Engine] has
 * the responsibility of actually logging a message. The [Verbosity] defines how verbose the [Log] is, respectively
 * which log messages get logged. For the order of verbosity see [Verbosity].
 *
 */
@Suppress("TooManyFunctions")
object Log {

    private lateinit var engine: Engine
    private lateinit var verbosity: Verbosity

    /**
     * Initialize the [Log] with a [Engine] and [Verbosity] level before using it.
     *
     * @param engine Where to log the actual messages.
     * @param verbosity Defines the level of [Verbosity]. All messages with [Verbosity] >= this value get logged when a
     * crash occurs. [WARN] is the least verbose and [DEBUG] the most.
     */
    fun init(engine: Engine, verbosity: Verbosity) {
        Log.engine = engine
        Log.verbosity = verbosity
    }

    private fun isLoggable(verbosity: Verbosity): Boolean = verbosity >= Log.verbosity

    /**
     * Logs a non-fatal crash and sends a crash report. Use this for exceptions that you can recover from and you want
     * to send a crash report.
     */
    fun exception(error: Throwable) {
        engine.exception(error)
    }

    /**
     * Use this to log exceptions, for which you don't want to send a crash report. This is always included in the crash
     * report and doesn't depend on the [verbosity].
     */
    fun e(tag: String, message: String, throwable: Throwable) {
        engine.e(tag, message, throwable)
    }

    /**
     * Use this to log events that can lead to a crash, but don't need to send a crash report. This doesn't send a crash
     * report. Only included in the crash report, if [verbosity] >= [WARN].
     */
    fun w(tag: String, message: String) {
        if (isLoggable(Verbosity.WARN)) {
            engine.w(tag, message)
        }
    }

    /**
     * Use this to log information, that gives you context of things that happened before a crash occurs. This doesn't
     * send a crash report. Only included in the crash report, if [verbosity] >= [INFO].
     */
    fun i(tag: String, message: String) {
        if (isLoggable(Verbosity.INFO)) {
            engine.i(tag, message)
        }
    }

    /**
     * Use this to log debug information, that are only interesting when debugging the code. This doesn't send a
     * crash report. Only included in the crash report, if [verbosity] >= [DEBUG].
     */
    fun d(tag: String, message: String) {
        if (isLoggable(Verbosity.DEBUG)) {
            engine.d(tag, message)
        }
    }

    /**
     * Sets or overrides the [key] with the given [value]. The [Engine] only sends the key/value pair when a fatal or
     * non-fatal crash occurs. Read more about crashes at [Log].
     */
    fun setValue(key: String, value: String) =
        engine.setValue(key, value)

    /**
     * Sets or overrides the [key] with the given [value]. The [Engine] only sends the key/value pair when a fatal or
     * non-fatal crash occurs. Read more about crashes at [Log].
     */
    fun setValue(key: String, value: Boolean) =
        engine.setValue(key, value)

    /**
     * Sets or overrides the [key] with the given [value]. The [Engine] only sends the key/value pair when a fatal or
     * non-fatal crash occurs. Read more about crashes at [Log].
     */
    fun setValue(key: String, value: Int) =
        engine.setValue(key, value)

    /**
     * Sets or overrides the [key] with the given [value]. The [Engine] only sends the key/value pair when a fatal or
     * non-fatal crash occurs. Read more about crashes at [Log].
     */
    fun setValue(key: String, value: Float) =
        engine.setValue(key, value)

    /**
     * Sets or overrides the [key] with the given [value]. The [Engine] only sends the key/value pair when a fatal or
     * non-fatal crash occurs. Read more about crashes at [Log].
     */
    fun setValue(key: String, value: Double) =
        engine.setValue(key, value)

    /**
     * The level of verbosity for crash report information.
     *
     * The order in terms of verbosity, from most to least is
     * [DEBUG], [INFO], [WARN], [ERROR]
     */
    enum class Verbosity {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    /**
     * A facade of logging messages. Implement this interface to forward the logging calls of [Log] to an actual
     * implementation.
     *
     * Don't use this interface to log messages. Use [Log] instead.
     */
    @Suppress("ComplexInterface")
    interface Engine {

        fun exception(throwable: Throwable)

        fun e(tag: String, message: String, throwable: Throwable)

        fun w(tag: String, message: String)

        fun i(tag: String, message: String)

        fun d(tag: String, message: String)

        fun setValue(key: String, value: String)

        fun setValue(key: String, value: Boolean)

        fun setValue(key: String, value: Int)

        fun setValue(key: String, value: Float)

        fun setValue(key: String, value: Double)
    }
}
