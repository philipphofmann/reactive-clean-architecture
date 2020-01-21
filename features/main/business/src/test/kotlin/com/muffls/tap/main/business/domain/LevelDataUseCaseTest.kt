package com.muffls.tap.main.business.domain

import com.muffls.tap.main.business.entities.Tap
import com.muffls.tap.main.business.entities.TestLevelCalculator
import com.muffls.testing.assertValue
import com.muffls.time.CurrentTime
import com.muffls.time.TestCurrentTimeProvider
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class LevelDataUseCaseTest {

    private lateinit var sut: LevelDataUseCase
    private lateinit var tapRepository: InMemoryTapRepository

    @Before
    fun before() {
        TestCurrentTimeProvider.init()
        tapRepository = InMemoryTapRepository()
        sut = LevelDataUseCase(tapRepository, TestLevelCalculator())
    }

    @Test
    fun `calculation of level progress is correctly forwarded`() = runBlockingTest {
        sut.data.assertValue(LevelData(0, 0.0, 0))

        tapRepository.save(Tap("1", CurrentTime.nowZonedDateTime))

        sut.data.assertValue(LevelData(1, 1.0, 1))
    }
}
