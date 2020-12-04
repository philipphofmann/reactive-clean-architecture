package com.muffls.tap.main.business.domain

import com.muffls.tap.main.business.entities.Tap
import com.muffls.testing.assertValue
import com.muffls.time.CurrentTime
import com.muffls.time.TestCurrentTimeProvider
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import java.util.UUID

class InMemoryTapRepositoryTest {

    private lateinit var sut: InMemoryTapRepository

    @Before
    fun before() {
        sut = InMemoryTapRepository()

        TestCurrentTimeProvider.init()
    }

    @Test
    fun `for each new tap a tap a new tapCount is sent`() = runBlockingTest {
        sut.tapCount.assertValue(0)

        sut.save()
        sut.tapCount.assertValue(1)

        sut.save()
        sut.tapCount.assertValue(2)

        sut.save()
        sut.tapCount.assertValue(3)
    }

    @Test
    fun `for each new tap all taps are sent`() = runBlockingTest {
        sut.all.assertValue(emptyList())

        saveTap("1")
        sut.all.assertValue(listOf(createTap("1")))

        saveTap("3")
        sut.all.assertValue(listOf(createTap("1"), createTap("3")))
    }

    private suspend fun saveTap(tapId: String = UUID.randomUUID().toString()) {
        sut.save(createTap(tapId))
    }

    private fun createTap(tapId: String) = Tap(tapId, CurrentTime.nowZonedDateTime)
}
