package com.antonzhdanov.smartweatherapp.domain.util

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab

interface NavigationTab : Tab {
    @Composable
    fun Actions()
}
