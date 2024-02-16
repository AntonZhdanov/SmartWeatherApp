package com.antonzhdanov.smartweatherapp.domain.weather

import com.antonzhdanov.smartweatherapp.domain.remote.HourlyWeatherUnitsDto
import java.time.LocalDateTime

data class HourlyWeatherData(
    val time: LocalDateTime,
    val temperature: Int,
    val apparentTemperature: Int,
    val weatherType: WeatherType,
    val precipitationProbability: Int?,
    val windSpeed: Int,
    val units: HourlyWeatherUnitsDto,
)
