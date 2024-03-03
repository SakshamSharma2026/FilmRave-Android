package com.codewithshadow.filmrave.di

import android.app.Application
import com.codewithshadow.filmrave.BuildConfig
import com.codewithshadow.filmrave.data.datasources.remote.ApiClient
import com.codewithshadow.filmrave.data.datasources.remote.RemoteDataSource
import com.codewithshadow.filmrave.data.repositories.SearchInfoRepositoryImpl
import com.codewithshadow.filmrave.domain.repositories.SearchInfoRepository
import com.codewithshadow.filmrave.domain.usecases.SearchListInfoUseCase
import com.codewithshadow.filmrave.utils.Constants.API_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideNetworkClient(appContext: Application): OkHttpClient {
        return OkHttpClient().newBuilder()
            .cache(Cache(appContext.cacheDir, (5 * 1024 * 1024).toLong()))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url =
                    originalHttpUrl.newBuilder()
                        .addQueryParameter(API_KEY, BuildConfig.MOVIE_API_KEY)
                        .build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideApiServices(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): ApiClient {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient).addConverterFactory(gsonConverterFactory)
            .build()
            .create(ApiClient::class.java)
    }


    @Singleton
    @Provides
    fun provideSearchInfoRepository(
        remote: RemoteDataSource,
        appContext: Application
    ): SearchInfoRepository {
        return SearchInfoRepositoryImpl(remote, appContext)
    }


    @Singleton
    @Provides
    fun provideSearchListInfoUseCase(repository: SearchInfoRepository): SearchListInfoUseCase {
        return SearchListInfoUseCase(repository)
    }
}