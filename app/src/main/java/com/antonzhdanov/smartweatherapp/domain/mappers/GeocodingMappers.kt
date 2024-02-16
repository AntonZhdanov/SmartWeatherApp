package com.antonzhdanov.smartweatherapp.domain.mappers

import com.antonzhdanov.smartweatherapp.domain.geocoding.GeocodingData
import com.antonzhdanov.smartweatherapp.domain.remote.GeocodingDto

fun GeocodingDto.toGeocodingData(): List<GeocodingData> {
    return results.map {
        GeocodingData(
            location = "${if(it.name == it.country) "" else "${it.name}, "}${if(it.admin == null) "" else "${it.admin}, "}${it.country}",
            longitude = it.longitude,
            latitude = it.latitude,
        )
    }
}
