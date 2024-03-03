package com.codewithshadow.filmrave.data.datasources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codewithshadow.filmrave.domain.models.HomeFeed
import com.codewithshadow.filmrave.domain.models.HomeFeedData
import com.codewithshadow.filmrave.domain.models.MovieResult
import com.codewithshadow.filmrave.utils.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class MovieDataEntity(
    val homeFeedList: List<HomeFeed>,
    val bannerMovie: List<MovieResult>,
    @PrimaryKey val id: Int? = null
) {

    fun toHomeFeedDataInfo(): HomeFeedData {
        return HomeFeedData(
            bannerMovie = bannerMovie,
            homeFeedResponseList = homeFeedList
        )
    }

}