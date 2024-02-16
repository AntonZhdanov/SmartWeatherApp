package com.antonzhdanov.smartweatherapp.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.antonzhdanov.smartweatherapp.R
import com.antonzhdanov.smartweatherapp.domain.util.NavigationTab
import com.antonzhdanov.smartweatherapp.presentation.components.location.LocationsDrawer
import com.antonzhdanov.smartweatherapp.presentation.components.navigation.BottomBar
import com.antonzhdanov.smartweatherapp.presentation.components.navigation.SmallToolbar
import com.antonzhdanov.smartweatherapp.presentation.tabs.TodayTab
import kotlinx.coroutines.launch

class MainScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val drawerState =
            rememberDrawerState(initialValue = DrawerValue.Closed)
        val coroutineScope = rememberCoroutineScope()

        TabNavigator(tab = TodayTab) {
            LocationsDrawer(
                drawerState = drawerState,
                onClose = { coroutineScope.launch { drawerState.close() } }
            ) {
                Scaffold(
                    topBar = {
                        SmallToolbar(
                            title = {
                                Text(
                                    text = it.current.options.title,
                                    maxLines = 1,
                                    modifier = Modifier.basicMarquee()
                                )
                            },
                            actions = {
                                (it.current as? NavigationTab)?.Actions()
                            }
                        ) {
                            IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = stringResource(id = R.string.location_picker_open)
                                )
                            }
                        }
                    },
                    bottomBar = {
                        BottomBar(navigator = it)
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        CurrentScreen()
                    }
                }
            }
        }
    }
}
