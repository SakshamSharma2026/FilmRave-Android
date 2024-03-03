package com.codewithshadow.filmrave.data.datasources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codewithshadow.filmrave.domain.models.MovieResult
import com.codewithshadow.filmrave.utils.Constants.WATCHLIST_TABLE_NAME

@Entity(tableName = WATCHLIST_TABLE_NAME)
data class WatchListEntity(
    val bannerMovie: MovieResult,
    @PrimaryKey val id: Int
)