package com.antonzhdanov.smartweatherapp.presentation.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.GpsFixed
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.antonzhdanov.smartweatherapp.R
import com.antonzhdanov.smartweatherapp.domain.util.NavigationTab
import com.antonzhdanov.smartweatherapp.presentation.components.settings.SettingsCategory
import com.antonzhdanov.smartweatherapp.presentation.screen.AboutScreen
import com.antonzhdanov.smartweatherapp.presentation.screen.AppearanceSettingsScreen
import com.antonzhdanov.smartweatherapp.presentation.screen.LocationPickerScreen
import com.antonzhdanov.smartweatherapp.presentation.screen.UnitsScreen


object SettingsTab : NavigationTab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.tab_settings)
            val icon = rememberVectorPainter(Icons.Default.Settings)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Column {
            SettingsCategory(
                icon = Icons.Outlined.Palette,
                text = stringResource(R.string.settings_appearance),
                subtext = stringResource(R.string.settings_appearance_description),
                destination = ::AppearanceSettingsScreen
            )
            SettingsCategory(
                icon = Icons.Outlined.GpsFixed,
                text = stringResource(R.string.settings_location),
                subtext = stringResource(R.string.settings_location_description),
                destination = ::LocationPickerScreen
            )
            SettingsCategory(
                icon = Icons.Outlined.AcUnit,
                text = stringResource(R.string.settings_units),
                subtext = stringResource(R.string.settings_units_description),
                destination = ::UnitsScreen
            )
        }
    }

    @Composable
    override fun Actions() {
        val navigator = LocalNavigator.current?.parent

        IconButton(onClick = { navigator?.push(AboutScreen()) }) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = stringResource(R.string.action_open_about)
            )
        }
    }
}
