package com.codewithshadow.filmrave.domain.repositories

import com.codewithshadow.filmrave.data.datasources.local.entity.WatchListEntity
import com.codewithshadow.filmrave.domain.models.WatchProviders
import com.codewithshadow.filmrave.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface WatchListInfoRepository {

    fun getWatchListInfo(): Flow<List<WatchListEntity>>
    suspend fun insertWatchListInfo(watchListEntity: WatchListEntity)

    suspend fun deleteWatchListInfo(watchListEntity: WatchListEntity)

    fun getMovieWatchProviders(movieId: Int): Flow<NetworkResult<WatchProviders>>

}