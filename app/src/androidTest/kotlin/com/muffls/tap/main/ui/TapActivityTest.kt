package com.muffls.tap.main.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.muffls.tap.main.repository.db.MainDatabase
import com.muffls.testing.runBlockingWithTimeOut
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TapActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(TappingActivity::class.java)

    private val appContext = ApplicationProvider.getApplicationContext<Context>()
    private val tapDao = MainDatabase.getInstance(appContext).tapDao()

    @Before
    fun before() = runBlockingWithTimeOut {
        tapDao.deleteAll()
    }

    @Test
    fun tapCountGetsSet() {
        onView(withId(R.id.tapCount))
                .check(matches(withText("0")))
                .perform(click())
                .check(matches(withText("1")))
    }

    @Test
    fun levelGetsSet() {
        onView(withId(R.id.tapLevel))
                .check(matches(withText(getLevelText(0))))
                .perform(click())
                .perform(click())
                .perform(click())
                .check(matches(withText(getLevelText(1))))
    }

    private fun getLevelText(level: Int): String {
        return "${appContext.getString(R.string.tap_level)} $level"
    }
}
