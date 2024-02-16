package com.antonzhdanov.smartweatherapp

import android.app.Application
import com.antonzhdanov.smartweatherapp.di.appModule
import com.antonzhdanov.smartweatherapp.di.managerModule
import com.antonzhdanov.smartweatherapp.di.repositoryModule
import com.antonzhdanov.smartweatherapp.di.screenModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class SmartWeather : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SmartWeather)
            modules(
                appModule, repositoryModule, screenModelModule, managerModule
            )
        }
    }
}
