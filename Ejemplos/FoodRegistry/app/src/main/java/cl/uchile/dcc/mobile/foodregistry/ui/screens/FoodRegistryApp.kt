package cl.uchile.dcc.mobile.foodregistry.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.sharp.AddCard
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.uchile.dcc.mobile.foodregistry.ui.components.FoodRegistryNavigationDrawer
import cl.uchile.dcc.mobile.foodregistry.ui.components.MainBottomNavigation
import cl.uchile.dcc.mobile.foodregistry.viewmodel.FoodRegistryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodRegistryApp(
    viewModel: FoodRegistryViewModel = viewModel()
) {
    val currentRoute by viewModel.currentRoute.collectAsState()
    val eventState by viewModel.uiState.collectAsStateWithLifecycle()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(currentRoute) {
        snackbarHostState.showSnackbar("Ruta: $currentRoute")
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            FoodRegistryNavigationDrawer(
                viewModel = viewModel,
                onCloseDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = currentRoute.title) },
                    navigationIcon = {
                        if (currentRoute != ScreenRoutes.OVERVIEW) {
                            IconButton(
                                onClick = {
                                    viewModel.goBack()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Sharp.ArrowBack,
                                    contentDescription = "Volver"
                                )
                            }
                        }
                        else {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Sharp.AddCard,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                )
            },
            bottomBar = {
                MainBottomNavigation(
                    currentRoute = currentRoute.route,
                    onNavigateTo = { viewModel.navigateTo(it) }
                )
            },
            snackbarHost = {
                // PENDIENTE
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                when (currentRoute) {
                    ScreenRoutes.OVERVIEW -> OverviewScreen()
                    ScreenRoutes.REGISTRY -> RegistryScreen()
                    ScreenRoutes.HISTORY -> HistoryScreen()
                    ScreenRoutes.FOODS -> FoodScreen()
                    ScreenRoutes.SETTINGS -> SettingsScreen()
                    else -> {
                        Text(text = "¡Ruta no implementada!")
                    }
                }
            }
        }
    }
}