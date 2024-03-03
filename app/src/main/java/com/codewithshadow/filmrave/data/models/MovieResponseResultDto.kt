package com.codewithshadow.filmrave.data.models

import com.codewithshadow.filmrave.domain.models.MovieResult
import com.google.gson.annotations.SerializedName

data class MovieResponseResultDto(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    val id: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
) {
    fun toMovieResult(): MovieResult {
        return MovieResult(
            backdropPath = backdropPath,
            title = title,
            id = id,
            voteAverage = voteAverage,
            posterPath = posterPath,
            overview = overview,
            releaseDate = releaseDate,
            genreIds = genreIds,
            originalLanguage = originalLanguage
        )
    }
}