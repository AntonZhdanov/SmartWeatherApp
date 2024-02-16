package com.antonzhdanov.smartweatherapp.di

import com.antonzhdanov.smartweatherapp.presentation.screenmodel.AppearancePreferencesScreenModel
import com.antonzhdanov.smartweatherapp.presentation.screenmodel.DailyWeatherScreenModel
import com.antonzhdanov.smartweatherapp.presentation.screenmodel.HourlyWeatherScreenModel
import com.antonzhdanov.smartweatherapp.presentation.screenmodel.LocationPickerScreenModel
import com.antonzhdanov.smartweatherapp.presentation.screenmodel.UnitPreferencesScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val screenModelModule = module {
    factoryOf(::AppearancePreferencesScreenModel)
    factoryOf(::UnitPreferencesScreenModel)
    factoryOf(::LocationPickerScreenModel)
    factoryOf(::HourlyWeatherScreenModel)
    factoryOf(::DailyWeatherScreenModel)
}
