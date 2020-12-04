package com.muffls.tap.main.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.muffls.tap.main.business.entities.Tap
import com.muffls.tap.main.repository.db.MainDatabase
import com.muffls.tap.main.repository.db.TapDao
import com.muffls.testing.assertValue
import com.muffls.testing.runBlockingWithTimeOut
import com.muffls.time.CurrentTime
import com.muffls.time.TestCurrentTimeProvider
import org.junit.Before
import org.junit.Test

class DefaultTapRepositoryTest {

    private lateinit var sut: DefaultTapRepository
    private lateinit var tapDao: TapDao

    @Before
    fun before() = runBlockingWithTimeOut {
        TestCurrentTimeProvider.init()

        val appContext = ApplicationProvider.getApplicationContext<Context>()
        tapDao = MainDatabase.getInstance(appContext).tapDao()
        tapDao.deleteAll()

        sut = DefaultTapRepository(tapDao)
    }

    @Test
    fun noTaps() = runBlockingWithTimeOut {
        sut.tapCount.assertValue(0L)
        sut.all.assertValue(emptyList())
    }

    @Test
    fun addThreeTaps() = runBlockingWithTimeOut {
        addTap("1")
        addTap("2")
        addTap("3")

        sut.tapCount.assertValue(3L)

        sut.all.assertValue(
            listOf(
                Tap("1", CurrentTime.nowZonedDateTime),
                Tap("2", CurrentTime.nowZonedDateTime),
                Tap("3", CurrentTime.nowZonedDateTime)
            )
        )
    }

    private suspend fun addTap(tapId: String) {
        sut.save(Tap(tapId, CurrentTime.nowZonedDateTime))
    }
}
