package com.antonzhdanov.smartweatherapp.presentation.components.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LargeToolbar(
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    backButton: Boolean = false
) {
    LargeTopAppBar(
        title = title,
        navigationIcon = { if (backButton) BackButton() },
        actions = actions,
    )
}
