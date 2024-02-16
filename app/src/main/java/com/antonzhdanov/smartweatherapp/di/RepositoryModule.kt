package com.antonzhdanov.smartweatherapp.di

import com.antonzhdanov.smartweatherapp.domain.repository.GeocodingRepository
import com.antonzhdanov.smartweatherapp.domain.repository.WeatherRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::WeatherRepository)
    singleOf(::GeocodingRepository)
}
