package com.antonzhdanov.smartweatherapp.domain.weather

data class HourlyWeatherInfo(
    val weatherData: List<HourlyWeatherData>,
    val currentWeatherData: HourlyWeatherData?,
)
