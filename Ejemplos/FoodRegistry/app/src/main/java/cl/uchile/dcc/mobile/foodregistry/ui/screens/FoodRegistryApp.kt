package cl.uchile.dcc.mobile.foodregistry.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.sharp.AddCard
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.uchile.dcc.mobile.foodregistry.ui.components.FoodRegistryNavigationDrawer
import cl.uchile.dcc.mobile.foodregistry.ui.components.MainBottomNavigation
import cl.uchile.dcc.mobile.foodregistry.viewmodel.FoodRegistryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodRegistryApp(
    viewModel: FoodRegistryViewModel = viewModel()
) {
    val eventState by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val foodRegistryId by viewModel.foodRegistryId.collectAsStateWithLifecycle()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            FoodRegistryNavigationDrawer(
                viewModel = viewModel,
                navController = navController,
                onCloseDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        val title = when (navController.currentBackStackEntry?.destination?.route) {
            ScreenRoutes.OVERVIEW.route -> ScreenRoutes.OVERVIEW.title
            ScreenRoutes.REGISTRY.route -> ScreenRoutes.REGISTRY.title
            ScreenRoutes.HISTORY.route -> ScreenRoutes.HISTORY.title
            else -> "No Title"
        }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = title
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
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
                    currentRoute = navController.currentBackStackEntry?.destination?.route,
                    onNavigateTo = { navController.navigate(it) }
                )
            },
            snackbarHost = {
                // PENDIENTE
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                val startDestination = ScreenRoutes.OVERVIEW.route
                if (foodRegistryId.isNotEmpty()) {
                    navController.navigate(ScreenRoutes.HISTORY_DETAILS.route)
                }

                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable(ScreenRoutes.OVERVIEW.route) {
                        OverviewScreen(
                            viewModel = viewModel,
                            onNavigate = { navController.navigate(it) }
                        )
                    }
                    composable(ScreenRoutes.REGISTRY.route) {
                        RegistryScreen(
                            viewModel = viewModel,
                            onNavigate = { navController.navigate(it) }
                        )
                    }
                    composable(ScreenRoutes.HISTORY.route) {
                        HistoryScreen(
                            viewModel = viewModel,
                            onNavigate = { navController.navigate(it) }
                        )
                    }
                }
            }
        }
    }
}