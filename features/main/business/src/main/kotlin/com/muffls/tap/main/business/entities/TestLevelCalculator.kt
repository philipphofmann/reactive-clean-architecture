package com.muffls.tap.main.business.entities

class TestLevelCalculator : LevelCalculator {

    override fun calculateLevel(tapCount: Long) = tapCount.toInt()

    override fun calculateLevelProgress(tapCount: Long) = tapCount.toDouble()
}
