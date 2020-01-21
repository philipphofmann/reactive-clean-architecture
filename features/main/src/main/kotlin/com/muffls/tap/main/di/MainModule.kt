package com.muffls.tap.main.di

import com.muffls.tap.main.business.entities.DefaultLevelCalculator
import com.muffls.tap.main.business.entities.LevelCalculator
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    internal fun providesTapLevelCalculator(): LevelCalculator = DefaultLevelCalculator
}
