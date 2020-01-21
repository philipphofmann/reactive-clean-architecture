package com.muffls.tap.main.business.domain

import com.muffls.tap.main.business.entities.LevelCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Suppress("UseDataClass")
class LevelDataUseCase @Inject constructor(
    private val repository: TapRepository,
    private val levelCalculator: LevelCalculator
) {
    val data: Flow<LevelData>
        get() = repository.tapCount.map { tapCount ->
            LevelData(
                levelCalculator.calculateLevel(tapCount),
                levelCalculator.calculateLevelProgress(tapCount),
                tapCount
            )
        }
}

data class LevelData(val level: Int, val progress: Double, val tapCount: Long)
