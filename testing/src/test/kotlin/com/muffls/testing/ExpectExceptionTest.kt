package com.muffls.testing

import org.junit.Assert.fail
import org.junit.Test

class ExpectExceptionTest {
    @Test
    fun `Expected exception is thrown`() {
        expectException<TestException> { throw TestException("TEST") }
    }

    @Test
    @Suppress("SwallowedException")
    fun `Expected exception, but nothing is thrown`() {
        var failed = false
        try {
            expectException<TestException> { }
            failed = true
        } catch (e: AssertionError) {
            // success!
        }
        if (failed) {
            fail() // fail cannot be thrown inside try-block, because it also throws an
            // AssertionError
        }
    }

    @Test
    @Suppress("SwallowedException")
    fun `Expected exception, but wrong exception is thrown`() {
        var failed = false
        try {
            expectException<TestException> { throw IndexOutOfBoundsException() }
            failed = true
        } catch (e: AssertionError) {
            // success!
        }
        if (failed) {
            fail()
        }
    }

    @Test
    fun `Subtype of expected exception is thrown`() {
        expectException<TestException> { throw TestSubException("TEST") }
    }

    open class TestException : Exception {
        constructor(message: String) : super(message)
        constructor(message: String, throwable: Throwable) : super(message, throwable)
    }

    open class TestSubException : TestException {
        constructor(message: String) : super(message)
        constructor(message: String, throwable: Throwable) : super(message, throwable)
    }
}
