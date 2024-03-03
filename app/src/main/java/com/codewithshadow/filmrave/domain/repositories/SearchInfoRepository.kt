package com.codewithshadow.filmrave.domain.repositories

import com.codewithshadow.filmrave.domain.models.MovieList
import com.codewithshadow.filmrave.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SearchInfoRepository {

    fun searchMovie(searchQuery:String): Flow<NetworkResult<MovieList>>
    fun trendingMovies(): Flow<NetworkResult<MovieList>>


}