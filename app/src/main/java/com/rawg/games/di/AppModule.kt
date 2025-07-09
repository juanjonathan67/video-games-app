package com.rawg.games.di

import android.app.Application
import com.rawg.games.BuildConfig
import com.rawg.games.data.network.service.games.GamesService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppModule {
    @Singleton
    @Provides
    @Named("okhttp_client")
    fun provideOkHttpClient(
        @Named("rawg_api_key") apiKey: String,
        application: Application
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()
                    val originalUrl = original.url

                    val newUrl = originalUrl.newBuilder()
                        .addQueryParameter("key", apiKey)
                        .build()

                    val newRequest = original.newBuilder()
                        .url(newUrl)
                        .build()

                    return chain.proceed(newRequest)
                }
            })
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
            .build()
    }


    @Singleton
    @Provides
    @Named("retrofit_instance")
    fun provideRetrofitInstance(
        @Named("base_url") baseUrl: String,
        @Named("okhttp_client") okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGamesService(
        @Named("retrofit_instance") retrofit: Retrofit
    ) : GamesService {
        return retrofit.create(GamesService::class.java)
    }

    @Singleton
    @Provides
    @Named("rawg_api_key")
    fun provideApiKey(): String = BuildConfig.RAWG_API_KEY

    @Singleton
    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}