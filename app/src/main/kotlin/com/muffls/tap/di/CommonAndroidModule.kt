package com.muffls.tap.di

import android.app.Application
import com.example.common.android.resource.DefaultResourceProvider
import com.example.common.android.resource.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommonAndroidModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication() = application

    @Provides
    fun providesResourceProvider(): ResourceProvider =
        DefaultResourceProvider(application)
}
