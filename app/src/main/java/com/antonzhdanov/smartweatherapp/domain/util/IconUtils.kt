package com.antonzhdanov.smartweatherapp.domain.util

import com.antonzhdanov.smartweatherapp.domain.weather.DailyWeatherData
import com.antonzhdanov.smartweatherapp.domain.weather.HourlyWeatherData

fun getIcon(
    data: HourlyWeatherData,
    dailyData: DailyWeatherData,
): Int {
    return if (data.time.isAfter(dailyData.sunrise) && data.time.isBefore(
            dailyData.sunset
        )
    ) data.weatherType.iconRes else data.weatherType.nightIconRes
}
