package com.muffls.tap.main.ui.di

import com.muffls.tap.main.ui.TappingActivity

interface MainInjector {
    fun inject(activity: TappingActivity)
}

interface MainInjectorProvider {
    fun getMainInjector(): MainInjector
}
