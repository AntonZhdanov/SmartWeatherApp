package com.antonzhdanov.smartweatherapp.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antonzhdanov.smartweatherapp.db.entity.WeatherEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(weatherData: WeatherEntity)

    @Query("SELECT * FROM weather_data WHERE latitude = :lat AND longitude = :longitude")
    suspend fun getWeatherData(lat: Float, longitude: Float): WeatherEntity?
}
