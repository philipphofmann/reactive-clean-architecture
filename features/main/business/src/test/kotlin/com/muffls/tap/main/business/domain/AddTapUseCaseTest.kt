package com.muffls.tap.main.business.domain

import com.muffls.common.business.UUIDGenerator
import com.muffls.tap.main.business.entities.Tap
import com.muffls.testing.assertValue
import com.muffls.testing.mock
import com.muffls.testing.uuid
import com.muffls.time.TestCurrentTimeProvider
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class AddTapUseCaseTest {

    private lateinit var sut: AddTapUseCase
    private lateinit var tapRepository: InMemoryTapRepository
    private lateinit var uuidGenerator: UUIDGenerator

    @Before
    fun before() {
        TestCurrentTimeProvider.init()
        tapRepository = InMemoryTapRepository()

        uuidGenerator = mock()
        `when`(uuidGenerator.randomUUID).thenReturn(uuid)

        sut = AddTapUseCase(tapRepository, uuidGenerator)
    }

    @Test
    fun `adding a tap saves the tap to the repository`() = runBlockingTest {
        sut.addTap()

        tapRepository.all.assertValue(listOf(Tap(uuid.toString(), TestCurrentTimeProvider.NOW)))
    }
}
