package com.antonzhdanov.smartweatherapp.db.converter

import com.antonzhdanov.smartweatherapp.db.entity.WeatherEntity
import com.antonzhdanov.smartweatherapp.domain.remote.DailyWeatherDataDto
import com.antonzhdanov.smartweatherapp.domain.remote.DailyWeatherUnitsDto
import com.antonzhdanov.smartweatherapp.domain.remote.HourlyWeatherDataDto
import com.antonzhdanov.smartweatherapp.domain.remote.HourlyWeatherUnitsDto
import com.antonzhdanov.smartweatherapp.domain.remote.WeatherDto

object WeatherConverter {
    fun toWeatherEntity(weatherDto: WeatherDto): WeatherEntity {
        return WeatherEntity(
            latitude = 0f,
            longitude = 0f,
            temperature = weatherDto.hourlyWeatherData.temperature.firstOrNull() ?: 0f,
            windSpeed = weatherDto.hourlyWeatherData.windSpeed.firstOrNull() ?: 0f

        )
    }

    fun toWeatherDto(weatherEntity: WeatherEntity): WeatherDto {
        return WeatherDto(
            hourlyWeatherData = HourlyWeatherDataDto(
                time = listOf(""),
                temperature = listOf(weatherEntity.temperature),
                apparentTemperature = listOf(0f),
                weatherCode = listOf(0),
                precipitationProbability = listOf(0),
                windSpeed = listOf(weatherEntity.windSpeed)
            ),
            hourlyUnits = HourlyWeatherUnitsDto(
                temperature = "",
                apparentTemperature = "",
                precipitationProbability = "",
                windSpeed = ""
            ),
            dailyWeatherData = DailyWeatherDataDto(
                date = listOf(""),
                weatherCode = listOf(0),
                sunrise = listOf(""),
                sunset = listOf(""),
                precipitationProbabilityMax = listOf(0),
                precipitationSum = listOf(0f),
                windSpeedMax = listOf(0f),
                temperatureMax = listOf(0f),
                temperatureMin = listOf(0f),
                apparentTemperatureMax = listOf(0f),
                apparentTemperatureMin = listOf(0f)
            ),
            dailyUnits = DailyWeatherUnitsDto(
                precipitationProbabilityMax = "",
                precipitationSum = "",
                windSpeedMax = "",
                temperatureMax = "",
                temperatureMin = "",
                apparentTemperatureMax = "",
                apparentTemperatureMin = ""
            )
        )
    }
}
