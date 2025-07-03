package com.rawg.games.di

import android.app.Application
import com.rawg.games.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class,
    RepositoryModule::class,
    DaggerViewModelFactoryModule::class,
])
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun inject(application: Application) : Builder

        fun build() : AppComponent
    }
}