package com.muffls.tap.di

import com.muffls.tap.main.di.MainComponent
import com.muffls.tap.main.di.MainModule
import com.muffls.tap.main.di.MainRepositoryModule
import dagger.Component
import javax.inject.Singleton

@Component(
        modules = [
            (CommonAndroidModule::class),
            (MainRepositoryModule::class),
            (MainModule::class)
        ]
)
@Singleton
interface AppComponent {
    fun mainComponent(): MainComponent
}
