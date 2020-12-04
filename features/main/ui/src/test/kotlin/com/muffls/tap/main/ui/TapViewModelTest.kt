package com.muffls.tap.main.ui

import com.example.common.android.resource.TestResourceProvider
import com.muffls.common.business.UUIDGenerator
import com.muffls.tap.main.business.domain.AddTapUseCase
import com.muffls.tap.main.business.domain.InMemoryTapRepository
import com.muffls.tap.main.business.domain.LevelDataUseCase
import com.muffls.tap.main.business.entities.TestLevelCalculator
import com.muffls.testing.MainCoroutineRule
import com.muffls.testing.assertValue
import com.muffls.testing.mock
import com.muffls.time.TestCurrentTimeProvider
import io.sentry.Sentry
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TapViewModelTest {

    @get:Rule var coroutineRule = MainCoroutineRule()

    private lateinit var sut: TapViewModel
    private lateinit var tapRepository: InMemoryTapRepository

    @Before
    fun before() {
        TestCurrentTimeProvider.init()
        Sentry.bindClient(mock())
        tapRepository = InMemoryTapRepository()

        sut = TapViewModel(
            TestResourceProvider(),
            AddTapUseCase(tapRepository, UUIDGenerator()),
            LevelDataUseCase(tapRepository, TestLevelCalculator())
        )
    }

    @Test
    fun `no single tap`() = runBlockingTest {
        sut.state.assertValue(
            TapViewModel.ViewState("0", 0, "${R.string.tap_level} 0")
        )
    }

    @Test
    fun `add one tap`() = runBlockingTest {
        sut.addTap()

        sut.state.assertValue(
            TapViewModel.ViewState("1", 1, "${R.string.tap_level} 1")
        )
    }
}
