package com.codewithshadow.filmrave.data.models

import com.codewithshadow.filmrave.domain.models.HomeFeed


data class HomeFeedResponseDto(
    val title: String,
    val list: List<MovieResponseResultDto>
) {
    fun toHomeFeed(): HomeFeed {
        return HomeFeed(title = title, list = list.map { it.toMovieResult() })
    }
}
