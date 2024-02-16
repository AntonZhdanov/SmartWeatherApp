package com.antonzhdanov.smartweatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.antonzhdanov.smartweatherapp.presentation.screen.LocationPickerScreen
import com.antonzhdanov.smartweatherapp.presentation.screen.MainScreen
import com.antonzhdanov.smartweatherapp.presentation.screenmodel.AppearancePreferenceManager
import com.antonzhdanov.smartweatherapp.presentation.screenmodel.LocationPreferenceManager
import com.antonzhdanov.smartweatherapp.presentation.screenmodel.Theme
import com.antonzhdanov.smartweatherapp.ui.theme.WeatherAppTheme
import org.koin.android.ext.android.inject

class SmartWeatherActivity : ComponentActivity() {
    private val prefs: AppearancePreferenceManager by inject()
    private val location: LocationPreferenceManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDark = when (prefs.theme) {
                Theme.SYSTEM -> isSystemInDarkTheme()
                Theme.LIGHT -> false
                Theme.DARK -> true
            }

            WeatherAppTheme(darkTheme = isDark, monet = prefs.monet) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Navigator(
                        screen = if (location.locations.isEmpty()) LocationPickerScreen() else MainScreen(),
                    ) {
                        SlideTransition(it)
                    }
                }
            }
        }
    }
}
