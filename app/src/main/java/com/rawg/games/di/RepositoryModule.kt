package com.rawg.games.di

import com.rawg.games.domain.repository.GamesRepository
import com.rawg.games.data.network.repository.GamesRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindGamesRepository(gamesRepositoryImpl: GamesRepositoryImpl): GamesRepository
}