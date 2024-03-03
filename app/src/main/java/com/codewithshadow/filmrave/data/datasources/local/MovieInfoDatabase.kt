package com.codewithshadow.filmrave.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codewithshadow.filmrave.data.datasources.local.entity.MovieDataEntity
import com.codewithshadow.filmrave.data.datasources.local.entity.WatchListEntity

@Database(
    entities = [MovieDataEntity::class, WatchListEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MovieDataTypeConverter::class)
abstract class MovieInfoDatabase : RoomDatabase() {
    abstract val dao: MovieDataDao
}