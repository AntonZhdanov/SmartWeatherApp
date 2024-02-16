package com.antonzhdanov.smartweatherapp.presentation.screenmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.antonzhdanov.smartweatherapp.domain.geocoding.GeocodingData
import com.antonzhdanov.smartweatherapp.domain.manager.BasePreferenceManager
import com.antonzhdanov.smartweatherapp.domain.repository.GeocodingRepository
import com.antonzhdanov.smartweatherapp.domain.util.Resource
import kotlinx.coroutines.launch

data class LocationPickerState(
    val locations: List<GeocodingData>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

class LocationPreferenceManager(context: Context) :
    BasePreferenceManager(context.getSharedPreferences("location", Context.MODE_PRIVATE)) {
    var locations by jsonPreference<List<GeocodingData>>(
        "locations",
        listOf()
    )
    var selectedIndex by intPreference("selected_location", 0)
}

class LocationPickerScreenModel(
    val prefs: LocationPreferenceManager,
    private val repository: GeocodingRepository,
) : ScreenModel {
    var state by mutableStateOf(LocationPickerState())
        private set

    fun loadGeolocationInfo(location: String) {
        screenModelScope.launch {
            state = state.copy(isLoading = true, error = null)

            state = when (val result =
                repository.getGeocodingData(
                    location = location
                )) {
                is Resource.Success -> {
                    state.copy(
                        locations = result.data,
                        isLoading = false,
                        error = null,
                    )
                }

                is Resource.Error -> {
                    state.copy(
                        locations = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}
