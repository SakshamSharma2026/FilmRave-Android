package com.codewithshadow.filmrave.domain.usecases

import com.codewithshadow.filmrave.data.datasources.local.entity.WatchListEntity
import com.codewithshadow.filmrave.domain.models.WatchProviders
import com.codewithshadow.filmrave.domain.repositories.WatchListInfoRepository
import com.codewithshadow.filmrave.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class WatchListInfoUseCase(
    private val repository: WatchListInfoRepository
) {
    fun getWatchListInfo(): Flow<List<WatchListEntity>> {
        return repository.getWatchListInfo()
    }

    suspend fun insertWatchListInfo(watchListEntity: WatchListEntity) {
        repository.insertWatchListInfo(watchListEntity)
    }

    suspend fun deleteWatchListInfo(watchListEntity: WatchListEntity) {
        repository.deleteWatchListInfo(watchListEntity)
    }

    fun getMovieWatchProviders(movieId: Int): Flow<NetworkResult<WatchProviders>> {
        return repository.getMovieWatchProviders(movieId)
    }
}