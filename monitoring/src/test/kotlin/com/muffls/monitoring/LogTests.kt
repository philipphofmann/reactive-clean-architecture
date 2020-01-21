package com.muffls.monitoring

import com.muffls.testing.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.never
import org.mockito.Mockito.verify

class LogTests {

    private lateinit var engine: Log.Engine
    private val throwable = Throwable()

    @Before
    fun before() {
        engine = mock()
    }

    @Test
    fun `error must logged when warning`() {
        initLogger(Log.Verbosity.WARN)

        Log.exception(throwable)

        verify(engine).exception(throwable)
    }

    @Test
    fun `error must logged when info`() {
        initLogger(Log.Verbosity.INFO)

        Log.exception(throwable)

        verify(engine).exception(throwable)
    }

    @Test
    fun `error must logged when debug`() {
        initLogger(Log.Verbosity.DEBUG)
        Log.exception(throwable)
        verify(engine).exception(throwable)
    }

    @Test
    fun `warn must not be logged when error`() {
        initLogger(Log.Verbosity.ERROR)

        Log.warning()

        verify(engine, never()).warning()
    }

    @Test
    fun `info must not be logged when warn`() {
        initLogger(Log.Verbosity.WARN)

        Log.i("", "")

        verify(engine, never()).i("", "")
    }

    @Test
    fun `warning must be logged when warning`() {
        initLogger(Log.Verbosity.WARN)

        Log.warning()

        verify(engine).warning()
    }

    @Test
    fun `info must not be logged when warning`() {
        initLogger(Log.Verbosity.WARN)

        Log.i("", "")

        verify(engine, never()).i("", "")
    }

    @Test
    fun `log all when debug`() {
        initLogger(Log.Verbosity.DEBUG)

        Log.e("", "", throwable)
        Log.d("", "")

        verify(engine).e("", "", throwable)
        verify(engine).d("", "")
    }

    private fun initLogger(verbosity: Log.Verbosity) = Log.init(engine, verbosity)

    private fun Log.warning() = this.w("", "")

    private fun Log.Engine.warning() = this.w("", "")
}
