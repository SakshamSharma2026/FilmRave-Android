package com.codewithshadow.filmrave.domain.usecases

import com.codewithshadow.filmrave.domain.models.HomeFeedData
import com.codewithshadow.filmrave.domain.models.MovieList
import com.codewithshadow.filmrave.domain.models.MovieVideoList
import com.codewithshadow.filmrave.domain.repositories.MovieInfoRepository
import com.codewithshadow.filmrave.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetMovieInfoUseCase(private val repository: MovieInfoRepository) {

    fun getHomeFeedData(): Flow<NetworkResult<HomeFeedData>> {
        return repository.getHomeFeedData()
    }

    fun getMovieRecommendationsData(movieId: Int): Flow<NetworkResult<MovieList>> {
        return repository.getMovieRecommendationsData(movieId)
    }

    fun getMovieTrailerData(movieId: Int): Flow<NetworkResult<MovieVideoList>> {
        return repository.getMovieTrailerData(movieId)
    }
}
