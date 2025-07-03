package com.rawg.games.di.home

import androidx.lifecycle.ViewModel
import com.rawg.games.di.ViewModelKey
import com.rawg.games.ui.screens.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}