package com.codewithshadow.filmrave.domain.usecases

import com.codewithshadow.filmrave.domain.models.MovieList
import com.codewithshadow.filmrave.domain.repositories.SearchInfoRepository
import com.codewithshadow.filmrave.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class SearchListInfoUseCase(
    private val repository: SearchInfoRepository
) {

    fun searchMovieData(searchQuery: String): Flow<NetworkResult<MovieList>> {
        return repository.searchMovie(searchQuery)
    }

    fun getTrendingMovies(): Flow<NetworkResult<MovieList>> {
        return repository.trendingMovies()
    }
}