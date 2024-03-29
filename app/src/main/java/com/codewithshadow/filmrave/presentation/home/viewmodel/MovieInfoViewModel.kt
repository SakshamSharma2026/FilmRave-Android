package com.codewithshadow.filmrave.presentation.home.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithshadow.filmrave.domain.models.HomeFeedData
import com.codewithshadow.filmrave.domain.models.MovieList
import com.codewithshadow.filmrave.domain.models.MovieVideoList
import com.codewithshadow.filmrave.domain.usecases.GetMovieInfoUseCase
import com.codewithshadow.filmrave.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val getMovieInfoUseCase: GetMovieInfoUseCase
) : ViewModel() {

    /** NETWORK CALL */

    private var _allFeedList: MutableLiveData<NetworkResult<HomeFeedData>> = MutableLiveData()
    var allFeedList: LiveData<NetworkResult<HomeFeedData>> = _allFeedList

    private var _recommendedMoviesList: MutableLiveData<NetworkResult<MovieList>> =
        MutableLiveData()
    var recommendedMoviesList: LiveData<NetworkResult<MovieList>> = _recommendedMoviesList

    private var _movieResponseVideoList: MutableLiveData<NetworkResult<MovieVideoList>> =
        MutableLiveData()
    var movieResponseVideoList: LiveData<NetworkResult<MovieVideoList>> = _movieResponseVideoList


    init {
        getMovieInfoData()
    }


    fun getMovieInfoData() = viewModelScope.launch {
        getMovieInfoUseCase.getHomeFeedData().onEach { result ->
            _allFeedList.value = result
        }.launchIn(this)
    }


    fun getRecommendedMovies(movieId: Int) = viewModelScope.launch {
        getMovieInfoUseCase.getMovieRecommendationsData(movieId).onEach { result ->
            _recommendedMoviesList.value = result
        }.launchIn(this)

    }


    fun getMovieTrailerData(movieId: Int) = viewModelScope.launch {
        getMovieInfoUseCase.getMovieTrailerData(movieId).onEach { result ->
            _movieResponseVideoList.value = result
        }.launchIn(this)

    }

}