package com.codewithshadow.filmrave.data.models

import com.codewithshadow.filmrave.domain.models.MovieVideoList

data class MovieResponseVideoListDto(
    val id: Int?,
    val results: List<MovieResponseVideoResultDto>?
) {
    fun toMovieVideoList(): MovieVideoList {
        return MovieVideoList(id, results?.map { it.toMovieVideoResult() })
    }
}