package com.muffls.tap.main.business.entities

import kotlin.math.exp
import kotlin.math.floor
import kotlin.math.ln

object DefaultLevelCalculator : LevelCalculator {
    override fun calculateLevel(tapCount: Long) =
        if (tapCount == 0L) 0 else floor(ln(tapCount.toDouble())).toInt()

    @Suppress("MagicNumber")
    override fun calculateLevelProgress(tapCount: Long): Double {
        val tapsPreviousLevel =
            if (calculateLevel(tapCount) == 0) 0 else tapsForLevel(calculateLevel(tapCount))

        val progress =
            (tapCount.toDouble() - tapsPreviousLevel) /
                (tapsForNextLevel(tapCount) - tapsPreviousLevel)

        return (progress * 100)
    }

    private fun tapsForNextLevel(tapCount: Long): Int {
        val nextLevel = calculateLevel(tapCount) + 1
        return tapsForLevel(nextLevel)
    }

    private fun tapsForLevel(level: Int) = exp(level.toDouble()).toInt()
}
