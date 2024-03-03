package com.codewithshadow.filmrave.presentation.watchlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithshadow.filmrave.data.datasources.local.entity.WatchListEntity
import com.codewithshadow.filmrave.domain.models.MovieResult
import com.codewithshadow.filmrave.domain.models.WatchProviders
import com.codewithshadow.filmrave.domain.usecases.WatchListInfoUseCase
import com.codewithshadow.filmrave.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val watchListInfoUseCase: WatchListInfoUseCase

) : ViewModel() {

    /** ROOM DATABASE */
    private var _watchList: MutableLiveData<List<WatchListEntity>> = MutableLiveData()
    var watchList: LiveData<List<WatchListEntity>> = _watchList

    private var _watchListProviders: MutableLiveData<NetworkResult<WatchProviders>> =
        MutableLiveData()
    var watchListProviders: LiveData<NetworkResult<WatchProviders>> = _watchListProviders


    fun getWatchListInfoData() {
        viewModelScope.launch {
            watchListInfoUseCase.getWatchListInfo().onEach { result ->
                _watchList.value = result
            }.launchIn(this)
        }
    }

    fun getWatchListProviders(movieId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            watchListInfoUseCase.getMovieWatchProviders(movieId).onEach { result ->
                _watchListProviders.postValue(result)
            }.launchIn(this)
        }


    fun insertWatchListInfoData(movieResponseResult: MovieResult) {
        movieResponseResult.id?.let { id ->
            val watchListEntity = WatchListEntity(movieResponseResult, id)
            viewModelScope.launch(Dispatchers.IO) {
                watchListInfoUseCase.insertWatchListInfo(watchListEntity)
            }
        }
    }


    fun deleteWatchListInfoData(movieResponseResult: MovieResult) {
        movieResponseResult.id?.let { id ->
            val watchListEntity = WatchListEntity(movieResponseResult, id)
            viewModelScope.launch(Dispatchers.IO) {
                watchListInfoUseCase.deleteWatchListInfo(watchListEntity)
            }
        }
    }


}