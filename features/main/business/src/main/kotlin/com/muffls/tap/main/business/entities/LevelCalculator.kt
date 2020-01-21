package com.muffls.tap.main.business.entities

interface LevelCalculator {
    fun calculateLevel(tapCount: Long): Int
    fun calculateLevelProgress(tapCount: Long): Double
}
