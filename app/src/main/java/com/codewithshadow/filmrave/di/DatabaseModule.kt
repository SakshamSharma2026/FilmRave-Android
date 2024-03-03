package com.codewithshadow.filmrave.di


import android.app.Application
import androidx.room.Room
import com.codewithshadow.filmrave.data.datasources.local.LocalDataSource
import com.codewithshadow.filmrave.data.datasources.local.MovieDataDao
import com.codewithshadow.filmrave.data.datasources.local.MovieDataTypeConverter
import com.codewithshadow.filmrave.data.datasources.local.MovieInfoDatabase
import com.codewithshadow.filmrave.data.datasources.remote.RemoteDataSource
import com.codewithshadow.filmrave.data.repositories.MovieInfoRepositoryImpl
import com.codewithshadow.filmrave.data.repositories.WatchListInfoRepositoryImpl
import com.codewithshadow.filmrave.domain.repositories.MovieInfoRepository
import com.codewithshadow.filmrave.domain.repositories.WatchListInfoRepository
import com.codewithshadow.filmrave.domain.usecases.GetMovieInfoUseCase
import com.codewithshadow.filmrave.domain.usecases.WatchListInfoUseCase
import com.codewithshadow.filmrave.utils.Constants.DATABASE_NAME
import com.codewithshadow.filmrave.utils.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(appContext: Application): MovieInfoDatabase {
        return Room.databaseBuilder(
            appContext,
            MovieInfoDatabase::class.java,
            DATABASE_NAME
        ).addTypeConverter(MovieDataTypeConverter(GsonParser(Gson())))
            .build()
    }


    @Singleton
    @Provides
    fun provideGetMovieInfoUseCase(repository: MovieInfoRepository): GetMovieInfoUseCase {
        return GetMovieInfoUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideMovieInfoRepository(
        remote: RemoteDataSource,
        local: LocalDataSource,
        appContext: Application
    ): MovieInfoRepository {
        return MovieInfoRepositoryImpl(remote, local, appContext)
    }


    @Singleton
    @Provides
    fun provideWatchListInfoUseCase(repository: WatchListInfoRepository): WatchListInfoUseCase {
        return WatchListInfoUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideWatchListInfoRepository(
        remote: RemoteDataSource,
        local: LocalDataSource,
        appContext: Application
    ): WatchListInfoRepository {
        return WatchListInfoRepositoryImpl(remote, local, appContext)
    }


    @Singleton
    @Provides
    fun provideDao(database: MovieInfoDatabase): MovieDataDao {
        return database.dao
    }
}