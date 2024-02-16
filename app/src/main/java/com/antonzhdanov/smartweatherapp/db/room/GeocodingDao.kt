package com.antonzhdanov.smartweatherapp.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antonzhdanov.smartweatherapp.db.entity.GeocodingEntity

@Dao
interface GeocodingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeocodingData(geocodingData: List<GeocodingEntity>)

    @Query("SELECT * FROM geocoding_data WHERE location = :location")
    suspend fun getGeocodingData(location: String): List<GeocodingEntity>
}
