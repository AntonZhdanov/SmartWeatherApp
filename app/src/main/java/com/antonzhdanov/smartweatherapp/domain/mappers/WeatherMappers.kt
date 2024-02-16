package com.antonzhdanov.smartweatherapp.domain.mappers

import com.antonzhdanov.smartweatherapp.domain.remote.DailyWeatherDataDto
import com.antonzhdanov.smartweatherapp.domain.remote.DailyWeatherUnitsDto
import com.antonzhdanov.smartweatherapp.domain.remote.HourlyWeatherDataDto
import com.antonzhdanov.smartweatherapp.domain.remote.HourlyWeatherUnitsDto
import com.antonzhdanov.smartweatherapp.domain.remote.WeatherDto
import com.antonzhdanov.smartweatherapp.domain.weather.DailyWeatherData
import com.antonzhdanov.smartweatherapp.domain.weather.HourlyWeatherData
import com.antonzhdanov.smartweatherapp.domain.weather.HourlyWeatherInfo
import com.antonzhdanov.smartweatherapp.domain.weather.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

fun HourlyWeatherDataDto.toHourlyWeatherData(units: HourlyWeatherUnitsDto): List<HourlyWeatherData> {
    return time.subList(0, 24).mapIndexed { index, time ->
        HourlyWeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperature = temperature[index].roundToInt(),
            apparentTemperature = apparentTemperature[index].roundToInt(),
            windSpeed = windSpeed[index].roundToInt(),
            precipitationProbability = precipitationProbability.getOrNull(index),
            weatherType = WeatherType.fromWMO(weatherCode[index]),
            units = units.copy(
                windSpeed = units.windSpeed.replace("mp/h", "mph"),
            )
        )
    }
}

fun DailyWeatherDataDto.toDailyWeatherData(units: DailyWeatherUnitsDto): List<DailyWeatherData> {
    return date.mapIndexed { index, date ->
        DailyWeatherData(
            date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE),
            weatherType = WeatherType.fromWMO(weatherCode[index]),
            apparentTemperatureMax = apparentTemperatureMax[index].roundToInt(),
            apparentTemperatureMin = apparentTemperatureMin[index].roundToInt(),
            temperatureMax = temperatureMax[index].roundToInt(),
            temperatureMin = temperatureMin[index].roundToInt(),
            precipitationProbabilityMax = precipitationProbabilityMax.getOrNull(index),
            windSpeedMax = windSpeedMax[index].roundToInt(),
            sunrise = LocalDateTime.parse(sunrise[index]),
            sunset = LocalDateTime.parse(sunset[index]),
            precipitationSum = precipitationSum[index],
            units = units.copy(
                precipitationSum = units.precipitationSum.replace("inch", "\""),
                windSpeedMax = units.windSpeedMax.replace("mp/h", "mph"),
            )
        )
    }
}

fun WeatherDto.toHourlyWeatherInfo(): HourlyWeatherInfo {
    val weatherDataMap = hourlyWeatherData.toHourlyWeatherData(units = hourlyUnits)
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap.find {
        it.time.hour == now.hour
    }
    return HourlyWeatherInfo(
        weatherData = weatherDataMap,
        currentWeatherData = currentWeatherData,
    )
}
