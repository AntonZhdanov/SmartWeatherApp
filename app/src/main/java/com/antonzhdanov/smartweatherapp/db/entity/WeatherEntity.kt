package com.antonzhdanov.smartweatherapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 0,
    val latitude: Float,
    val longitude: Float,
    val temperature: Float,
    val windSpeed: Float
)
