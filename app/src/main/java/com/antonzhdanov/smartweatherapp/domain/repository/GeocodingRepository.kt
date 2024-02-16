package com.antonzhdanov.smartweatherapp.domain.repository

import com.antonzhdanov.smartweatherapp.db.room.GeocodingDao
import com.antonzhdanov.smartweatherapp.db.entity.GeocodingEntity
import com.antonzhdanov.smartweatherapp.domain.geocoding.GeocodingData
import com.antonzhdanov.smartweatherapp.domain.mappers.toGeocodingData
import com.antonzhdanov.smartweatherapp.domain.remote.GeocodingApi
import com.antonzhdanov.smartweatherapp.domain.util.Resource

class GeocodingRepository(private val api: GeocodingApi, private val geocodingDao: GeocodingDao) {
    suspend fun getGeocodingData(location: String): Resource<List<GeocodingData>> {
        return try {
            val cachedData = geocodingDao.getGeocodingData(location)
            if (cachedData.isNotEmpty()) {
                return Resource.Success(cachedData.map {
                    GeocodingData(location = it.location,
                        longitude = it.longitude,
                        latitude = it.latitude)
                })
            }

            val apiData = api.getGeocodingData(location = location).toGeocodingData()

            geocodingDao.insertGeocodingData(apiData.map {
                GeocodingEntity(
                    location = it.location,
                    longitude = it.longitude,
                    latitude = it.latitude
                )
            })

            Resource.Success(apiData)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}
