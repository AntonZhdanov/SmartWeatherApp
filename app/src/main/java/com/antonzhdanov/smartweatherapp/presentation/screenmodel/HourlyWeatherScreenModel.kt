package com.antonzhdanov.smartweatherapp.presentation.screenmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.antonzhdanov.smartweatherapp.domain.repository.WeatherRepository
import com.antonzhdanov.smartweatherapp.domain.util.Resource
import com.antonzhdanov.smartweatherapp.domain.weather.HourlyWeatherInfo
import kotlinx.coroutines.launch

data class HourlyWeatherState(
    val hourlyWeatherInfo: HourlyWeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val selected: Int? = null
)

class HourlyWeatherScreenModel(
    private val repository: WeatherRepository,
    private val unitsPreferenceManager: UnitPreferenceManager,
    val locationPreferenceManager: LocationPreferenceManager,
) : ScreenModel {
    var state by mutableStateOf(HourlyWeatherState())
        private set

    fun loadWeatherInfo(cache: Boolean = true) {
        val location = locationPreferenceManager.locations[locationPreferenceManager.selectedIndex]
        screenModelScope.launch {
            state = state.copy(isLoading = true, error = null, selected = null)
            state = when (val result =
                repository.getHourlyWeatherData(
                    lat = location.latitude,
                    long = location.longitude,
                    units = unitsPreferenceManager,
                    cache = cache
                )) {
                is Resource.Success -> {
                    state.copy(
                        hourlyWeatherInfo = result.data,
                        isLoading = false,
                        error = null,
                    )
                }

                is Resource.Error -> {
                    state.copy(
                        hourlyWeatherInfo = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    fun setSelected(index: Int) {
        state = state.copy(selected = index)
    }
}
