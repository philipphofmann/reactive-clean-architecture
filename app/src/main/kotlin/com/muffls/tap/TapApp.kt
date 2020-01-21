package com.muffls.tap

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.muffls.monitoring.Log
import com.muffls.monitoring.android.AndroidLogEngine
import com.muffls.tap.di.AppComponent
import com.muffls.tap.di.CommonAndroidModule
import com.muffls.tap.di.DaggerAppComponent
import com.muffls.tap.main.di.MainModule
import com.muffls.tap.main.di.MainRepositoryModule
import com.muffls.tap.main.ui.di.MainInjector
import com.muffls.tap.main.ui.di.MainInjectorProvider
import com.muffls.time.CurrentTime
import com.muffls.time.CurrentTimeProviderImpl

class TapApp : Application(), MainInjectorProvider {

    override fun getMainInjector(): MainInjector {
        return appComponent.mainComponent()
    }

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        Log.init(AndroidLogEngine(), BuildConfig.VERBOSITY)
        Log.i("TapApp", "Application onCreate started")

        AndroidThreeTen.init(this)
        CurrentTime.init(CurrentTimeProviderImpl())

        appComponent = DaggerAppComponent.builder()
            .commonAndroidModule(CommonAndroidModule(this))
            .mainModule(MainModule())
            .mainRepositoryModule(MainRepositoryModule())
            .build()

        Log.i("TapApp", "Application onCreate finished")
    }
}
