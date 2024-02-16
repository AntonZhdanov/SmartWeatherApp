package com.antonzhdanov.smartweatherapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "geocoding_data")
data class GeocodingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val location: String,
    val longitude: Float,
    val latitude: Float
)
