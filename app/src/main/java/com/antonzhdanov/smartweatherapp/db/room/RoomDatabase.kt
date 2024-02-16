package com.antonzhdanov.smartweatherapp.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antonzhdanov.smartweatherapp.db.entity.GeocodingEntity
import com.antonzhdanov.smartweatherapp.db.entity.WeatherEntity

@Database(entities = [WeatherEntity::class, GeocodingEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun geocodingDao(): GeocodingDao
}
