package com.muffls.tap

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.muffls.tap.di.AppComponent
import com.muffls.tap.di.CommonAndroidModule
import com.muffls.tap.di.DaggerAppComponent
import com.muffls.tap.main.di.MainModule
import com.muffls.tap.main.di.MainRepositoryModule
import com.muffls.tap.main.ui.di.MainInjector
import com.muffls.tap.main.ui.di.MainInjectorProvider
import com.muffls.time.CurrentTime
import com.muffls.time.CurrentTimeProviderImpl
import io.sentry.Sentry
import io.sentry.SentryLevel

class TapApp : Application(), MainInjectorProvider {

    override fun getMainInjector(): MainInjector {
        return appComponent.mainComponent()
    }

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
        CurrentTime.init(CurrentTimeProviderImpl())

        appComponent = DaggerAppComponent.builder()
            .commonAndroidModule(CommonAndroidModule(this))
            .mainModule(MainModule())
            .mainRepositoryModule(MainRepositoryModule())
            .build()

        Sentry.captureMessage("Application onCreate finished", SentryLevel.INFO)
    }
}
