package com.codewithshadow.filmrave.data.repositories

import android.app.Application
import com.codewithshadow.filmrave.data.datasources.local.LocalDataSource
import com.codewithshadow.filmrave.data.datasources.local.entity.MovieDataEntity
import com.codewithshadow.filmrave.data.datasources.remote.RemoteDataSource
import com.codewithshadow.filmrave.data.models.HomeFeedResponseDto
import com.codewithshadow.filmrave.domain.models.HomeFeedData
import com.codewithshadow.filmrave.domain.models.MovieList
import com.codewithshadow.filmrave.domain.models.MovieVideoList
import com.codewithshadow.filmrave.domain.repositories.MovieInfoRepository
import com.codewithshadow.filmrave.utils.Constants
import com.codewithshadow.filmrave.utils.NetworkResult
import com.codewithshadow.filmrave.utils.isNetworkAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.IOException


class MovieInfoRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val appContext: Application
) : MovieInfoRepository {

    // This function returns a Flow that emits the network result of fetching home feed data.
    override fun getHomeFeedData(): Flow<NetworkResult<HomeFeedData>> = flow {
        emit(NetworkResult.Loading())

        try {

            if (!isNetworkAvailable(appContext)) {
                emit(NetworkResult.Error("Please check your internet connection"))
                return@flow
            }

            withContext(Dispatchers.IO) {
                val nowPlayingMoviesListDef = async { remote.fetchNowPlayingMovies() }
                val popularMoviesListDef = async { remote.fetchPopularMovies() }
                val popularTvListDef = async { remote.fetchPopularTvShows() }
                val topRatedMoviesListDef = async { remote.fetchTopRatedMovies() }
                val animeSeriesListDef = async { remote.fetchAnimeSeries() }
                val bollywoodListDef = async { remote.fetchBollywoodMovies() }
                val netflixShowsListDef = async { remote.fetchNetflixTvShows() }
                val upcomingMoviesListDef = async { remote.fetchUpcomingMovies() }


                val nowPlayingMoviesList =
                    nowPlayingMoviesListDef.await().body()?.results ?: emptyList()
                val popularMoviesList =
                    popularMoviesListDef.await().body()?.results ?: emptyList()
                val popularTvList = popularTvListDef.await().body()?.results ?: emptyList()
                val topRatedMoviesList =
                    topRatedMoviesListDef.await().body()?.results ?: emptyList()
                val animeSeriesList = animeSeriesListDef.await().body()?.results ?: emptyList()
                val bollywoodList = bollywoodListDef.await().body()?.results ?: emptyList()
                val netflixShowsList =
                    netflixShowsListDef.await().body()?.results ?: emptyList()
                val upcomingMovies = upcomingMoviesListDef.await().body()?.results ?: emptyList()


                val wholeList = mutableListOf(
                    HomeFeedResponseDto(Constants.UPCOMING_MOVIES, upcomingMovies),
                    HomeFeedResponseDto(Constants.POPULAR_MOVIES, popularMoviesList),
                    HomeFeedResponseDto(Constants.POPULAR_TV_SHOWS, popularTvList),
                    HomeFeedResponseDto(Constants.TOP_RATED_MOVIES, topRatedMoviesList),
                    HomeFeedResponseDto(Constants.ANIME_SERIES, animeSeriesList),
                    HomeFeedResponseDto(Constants.BOLLYWOOD_MOVIES, bollywoodList),
                    HomeFeedResponseDto(Constants.NETFLIX_SHOWS, netflixShowsList)
                )

                // Store the data in local database
                val entity = MovieDataEntity(
                    bannerMovie = nowPlayingMoviesList.map { it.toMovieResult() },
                    homeFeedList = wholeList.map { it.toHomeFeed() }
                )
                local.deleteData()
                local.insertData(entity)
            }

            // Read the data from local database and convert it to HomeFeedData format
            val readData = local.readData().toHomeFeedDataInfo()

            // Emit the success state with the fetched data
            emit(
                NetworkResult.Success(
                    data = readData
                )
            )

        } catch (throwable: Throwable) {
            when (throwable) {

                is IOException -> {
                    emit(
                        NetworkResult.Error(
                            "Please check your internet connection"
                        )
                    )
                }

                else -> {
                    emit(
                        NetworkResult.Error(
                            throwable.message ?: "Something went wrong"
                        )
                    )
                }
            }
        }

    }


    // This function returns a Flow that emits the network result of fetching movie recommendations data.
    override fun getMovieRecommendationsData(movieId: Int): Flow<NetworkResult<MovieList>> =
        flow {
            emit(NetworkResult.Loading())
            try {
                if (isNetworkAvailable(appContext)) {
                    val apiResponse = remote.fetchRecommendedMovies(movieId)

                    // Emit the success state with the fetched data
                    emit(NetworkResult.Success(apiResponse.body()?.toMovieList()))
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        emit(
                            NetworkResult.Error(
                                "Please check your internet connection"
                            )
                        )
                    }

                    else -> {
                        emit(
                            NetworkResult.Error(
                                throwable.message ?: "Something went wrong"
                            )
                        )
                    }
                }
            }
        }


    // This function returns a Flow that emits the network result of fetching movie trailer data.
    override fun getMovieTrailerData(movieId: Int): Flow<NetworkResult<MovieVideoList>> = flow {
        emit(NetworkResult.Loading())
        try {
            if (isNetworkAvailable(appContext)) {
                val apiResponse = remote.fetchMovieVideo(movieId)
                // Emit the success state with the fetched data
                emit(NetworkResult.Success(apiResponse.body()?.toMovieVideoList()))
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    emit(
                        NetworkResult.Error(
                            "Please check your internet connection"
                        )
                    )
                }

                else -> {
                    emit(
                        NetworkResult.Error(
                            throwable.message ?: "Something went wrong"
                        )
                    )
                }
            }
        }
    }

}