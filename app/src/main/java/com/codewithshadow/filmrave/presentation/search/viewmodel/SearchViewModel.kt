package com.codewithshadow.filmrave.presentation.search.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithshadow.filmrave.domain.models.MovieList
import com.codewithshadow.filmrave.domain.usecases.SearchListInfoUseCase
import com.codewithshadow.filmrave.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchListInfoUseCase: SearchListInfoUseCase,
) : ViewModel() {

    private val _trendingMedia = MutableLiveData<NetworkResult<MovieList>>()
    val trendingMedia: LiveData<NetworkResult<MovieList>> = _trendingMedia

    private val _searchedMedia: MutableLiveData<NetworkResult<MovieList>> =
        MutableLiveData()
    val searchedMedia: LiveData<NetworkResult<MovieList>> = _searchedMedia

    fun trendingMovies() = viewModelScope.launch {
        viewModelScope.launch(Dispatchers.IO) {
            searchListInfoUseCase.getTrendingMovies().onEach { result ->
                _trendingMedia.postValue(result)
            }.launchIn(this)
        }
    }

    fun searchMedia(searchQuery: String) = viewModelScope.launch {
        viewModelScope.launch(Dispatchers.IO) {
            searchListInfoUseCase.searchMovieData(searchQuery).onEach { result ->
                _searchedMedia.postValue(result)
            }.launchIn(this)
        }
    }
}