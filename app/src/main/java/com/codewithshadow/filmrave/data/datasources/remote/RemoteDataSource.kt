package com.codewithshadow.filmrave.data.datasources.remote

import com.codewithshadow.filmrave.data.models.MovieResponseListDto
import com.codewithshadow.filmrave.data.models.MovieResponseVideoListDto
import com.codewithshadow.filmrave.data.models.WatchProvidersResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiClient: ApiClient
) {

    suspend fun fetchPopularMovies(): Response<MovieResponseListDto> {
        return apiClient.fetchPopularMoviesApiCall()
    }

    suspend fun fetchNowPlayingMovies(): Response<MovieResponseListDto> {
        return apiClient.fetchNowPlayingMoviesApiCall()
    }


    suspend fun fetchTopRatedMovies(): Response<MovieResponseListDto> {
        return apiClient.fetchTopRatedMoviesApiCall()
    }


    suspend fun fetchPopularTvShows(): Response<MovieResponseListDto> {
        return apiClient.fetchPopularTvShowsApiCall()
    }


    suspend fun fetchNetflixTvShows(): Response<MovieResponseListDto> {
        return apiClient.fetchNetflixTvShowsApiCall()
    }

    suspend fun fetchUpcomingMovies(): Response<MovieResponseListDto> {
        return apiClient.fetchUpcomingMoviesApiCall()
    }

    suspend fun fetchAnimeSeries(): Response<MovieResponseListDto> {
        return apiClient.fetchAnimeSeriesApiCall()
    }


    suspend fun fetchBollywoodMovies(): Response<MovieResponseListDto> {
        return apiClient.fetchBollywoodMoviesApiCall()
    }

    suspend fun fetchRecommendedMovies(movieId: Int): Response<MovieResponseListDto> {
        return apiClient.fetchRecommendedMoviesApiCall(movieId)
    }


    suspend fun fetchMovieSearchedResults(query: String): Response<MovieResponseListDto> {
        return apiClient.fetchMovieSearchedResultsApiCall(searchQuery = query)
    }

    suspend fun fetchTrending(): Response<MovieResponseListDto> {
        return apiClient.fetchTrendingApiCall()
    }

    suspend fun fetchMovieVideo(movieId: Int): Response<MovieResponseVideoListDto> {
        return apiClient.fetchMovieVideoApiCall(movieId)
    }

    suspend fun fetchMovieWatchProviders(movieId: Int): Response<WatchProvidersResponse> {
        return apiClient.getMovieWatchProvidersApiCall(movieId)
    }

}