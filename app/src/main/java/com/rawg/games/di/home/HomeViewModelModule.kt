package com.rawg.games.di.home

import androidx.lifecycle.ViewModel
import com.rawg.games.di.ViewModelKey
import com.rawg.games.ui.screens.home.HomeScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeScreenViewModel::class)
    abstract fun bindHomeScreenViewModel(viewModel: HomeScreenViewModel): ViewModel
}