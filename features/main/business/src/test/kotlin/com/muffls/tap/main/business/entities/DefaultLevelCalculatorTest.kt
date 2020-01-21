package com.muffls.tap.main.business.entities

import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultLevelCalculatorTest {

    private val sut = DefaultLevelCalculator
    private var actual: Int = 0

    @Test
    fun calculateLevel_WithTapCount0() {
        calculate(0)

        thenLevelShouldBe(0)
    }

    @Test
    fun calculateLevel_WithTapCount1() {
        calculate(2)

        thenLevelShouldBe(0)
    }

    @Test
    fun calculateLevel_WithTapCount3() {
        calculate(3)

        thenLevelShouldBe(1)
    }

    private fun calculate(tapCount: Long) {
        actual = sut.calculateLevel(tapCount)
    }

    private fun thenLevelShouldBe(expected: Int) {
        assertEquals(expected, actual)
    }

    @Test
    fun calculateLevelProgress() {
        testLevelProgress(0, 0.0)
        testLevelProgress(1, 50.0)
        testLevelProgress(2, 100.0)
        testLevelProgress(3, 20.0)
        testLevelProgress(4, 40.0)
    }

    private fun testLevelProgress(
        tapCount: Long,
        expectedProgress: Double
    ) {
        val actual = sut.calculateLevelProgress(tapCount)
        assertEquals(expectedProgress, actual, 0.0001)
    }
}
