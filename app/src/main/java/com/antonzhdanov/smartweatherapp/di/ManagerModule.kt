package com.antonzhdanov.smartweatherapp.di

import com.antonzhdanov.smartweatherapp.presentation.screenmodel.AppearancePreferenceManager
import com.antonzhdanov.smartweatherapp.presentation.screenmodel.LocationPreferenceManager
import com.antonzhdanov.smartweatherapp.presentation.screenmodel.UnitPreferenceManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managerModule = module {

    singleOf(::AppearancePreferenceManager)
    singleOf(::UnitPreferenceManager)
    singleOf(::LocationPreferenceManager)
}
