package com.antonzhdanov.smartweatherapp.domain.repository

import com.antonzhdanov.smartweatherapp.db.room.WeatherDao
import com.antonzhdanov.smartweatherapp.db.converter.WeatherConverter
import com.antonzhdanov.smartweatherapp.domain.mappers.toDailyWeatherData
import com.antonzhdanov.smartweatherapp.domain.mappers.toHourlyWeatherInfo
import com.antonzhdanov.smartweatherapp.domain.remote.WeatherApi
import com.antonzhdanov.smartweatherapp.domain.remote.WeatherDto
import com.antonzhdanov.smartweatherapp.domain.util.Resource
import com.antonzhdanov.smartweatherapp.domain.weather.DailyWeatherData
import com.antonzhdanov.smartweatherapp.domain.weather.HourlyWeatherInfo
import com.antonzhdanov.smartweatherapp.presentation.screenmodel.UnitPreferenceManager

class WeatherRepository(private val api: WeatherApi, private val weatherDao: WeatherDao) {
    suspend fun getDailyWeatherData(
        lat: Float,
        long: Float,
        units: UnitPreferenceManager,
        cache: Boolean = true
    ): Resource<List<DailyWeatherData>> {
        return try {
            val weatherData = getWeatherData(lat, long, units, cache)
            Resource.Success(weatherData.dailyWeatherData.toDailyWeatherData(weatherData.dailyUnits))
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    suspend fun getHourlyWeatherData(
        lat: Float,
        long: Float,
        units: UnitPreferenceManager,
        cache: Boolean = true
    ): Resource<HourlyWeatherInfo> {
        return try {
            val weatherData = getWeatherData(lat, long, units, cache)
            Resource.Success(weatherData.toHourlyWeatherInfo())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    private suspend fun getWeatherData(
        lat: Float,
        long: Float,
        units: UnitPreferenceManager,
        cache: Boolean = true
    ): WeatherDto {
        return if (cache) {
            getCachedWeatherData(lat, long)
                ?: api.getWeatherData(
                    lat,
                    long,
                    units.tempUnit.name.lowercase(),
                    units.windUnit.name.lowercase(),
                    units.precipitationUnit.name.lowercase()
                ).also { saveWeatherDataToCache(it) }
        } else {
            api.getWeatherDataWithoutCache(
                lat,
                long,
                units.tempUnit.name,
                units.windUnit.name,
                units.precipitationUnit.name
            )
        }
    }

    private suspend fun saveWeatherDataToCache(weatherData: WeatherDto) {
        val entity = WeatherConverter.toWeatherEntity(weatherData)
        weatherDao.insertWeatherData(entity)
    }

    private suspend fun getCachedWeatherData(lat: Float, longitude: Float): WeatherDto? {
        val entity = weatherDao.getWeatherData(lat, longitude)
        return entity?.let {
            WeatherConverter.toWeatherDto(it)
        }
    }
}
