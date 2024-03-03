package com.codewithshadow.filmrave.domain.repositories

import com.codewithshadow.filmrave.domain.models.HomeFeedData
import com.codewithshadow.filmrave.domain.models.MovieList
import com.codewithshadow.filmrave.domain.models.MovieVideoList
import com.codewithshadow.filmrave.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovieInfoRepository {

    fun getHomeFeedData(): Flow<NetworkResult<HomeFeedData>>
    fun getMovieRecommendationsData(movieId:Int): Flow<NetworkResult<MovieList>>
    fun getMovieTrailerData(movieId:Int): Flow<NetworkResult<MovieVideoList>>

}