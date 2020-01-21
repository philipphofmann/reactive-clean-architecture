package com.muffls.testing

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy

class MockitoExtensionsTest {
    class SampleMock {
        fun doNothing() {
        }
    }

    @Test
    fun mock() {
        val toMock = mock<SampleMock>()
        toMock.doNothing()

        `when`(toMock.doNothing()).thenThrow(IllegalArgumentException(""))

        expectException<IllegalArgumentException> { toMock.doNothing() }
    }

    @Test
    fun isMock() {
        val toMock = mock<SampleMock>()

        assertTrue(toMock.isMock)
        assertFalse(toMock.isSpy)
    }

    @Test
    fun isSpy() {
        val toMock = spy(SampleMock::class.java)

        assertTrue(toMock.isSpy)
        assertTrue(toMock.isMock)
    }
}
