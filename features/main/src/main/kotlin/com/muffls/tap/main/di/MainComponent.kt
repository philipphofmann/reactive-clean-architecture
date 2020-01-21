package com.muffls.tap.main.di

import com.muffls.tap.main.ui.di.MainInjector
import dagger.Subcomponent

@Subcomponent(
    modules = [
        MainRepositoryModule::class,
        MainModule::class
    ]
)
interface MainComponent : MainInjector
