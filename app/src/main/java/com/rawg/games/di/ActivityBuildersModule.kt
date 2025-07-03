package com.rawg.games.di

import com.rawg.games.MainActivity
import com.rawg.games.di.home.HomeViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [HomeViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}