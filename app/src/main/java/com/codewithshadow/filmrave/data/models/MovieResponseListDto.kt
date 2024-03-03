package com.codewithshadow.filmrave.data.models

import com.codewithshadow.filmrave.domain.models.MovieList
import com.google.gson.annotations.SerializedName

data class MovieResponseListDto(
    val page: Int?,
    val results: List<MovieResponseResultDto>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
) {
    fun toMovieList(): MovieList {
        return MovieList(results?.map { it.toMovieResult() })
    }
}