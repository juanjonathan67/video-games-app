package com.rawg.games.di

import com.rawg.games.MainActivity
import com.rawg.games.di.detail.DetailViewModelModule
import com.rawg.games.di.home.HomeViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [
            HomeViewModelModule::class,
            DetailViewModelModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}