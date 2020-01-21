package com.muffls.tap.main.di

import android.app.Application
import com.muffls.tap.main.business.domain.TapRepository
import com.muffls.tap.main.repository.DefaultTapRepository
import com.muffls.tap.main.repository.db.MainDatabase
import com.muffls.tap.main.repository.db.TapDao
import dagger.Module
import dagger.Provides

@Module
class MainRepositoryModule {

    @Provides
    internal fun providesAppDatabase(application: Application) =
            MainDatabase.getInstance(application)

    @Provides
    internal fun providesTapDao(mainDatabase: MainDatabase) = mainDatabase.tapDao()

    @Provides
    internal fun providesTapRepository(tapDao: TapDao): TapRepository = DefaultTapRepository(tapDao)
}
