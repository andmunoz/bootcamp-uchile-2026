package cl.uchile.dcc.mobile.peoplecounter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.uchile.dcc.mobile.peoplecounter.ui.screen.ScreenEnum
import cl.uchile.dcc.mobile.peoplecounter.ui.screen.PeopleCounter
import cl.uchile.dcc.mobile.peoplecounter.ui.screen.PeopleHistory
import cl.uchile.dcc.mobile.peoplecounter.ui.screen.PeopleRegistry
import cl.uchile.dcc.mobile.peoplecounter.ui.theme.PeopleCounterTheme
import cl.uchile.dcc.mobile.peoplecounter.viewmodel.MainScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Se instancia el viewModel que tiene el estado de la pantalla
        val screenViewModel = MainScreenViewModel()
        setContent {
            PeopleCounterTheme {
                MainScreen(screenViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(screenViewModel: MainScreenViewModel = viewModel()) {
    var actualScreen by remember { mutableStateOf( screenViewModel.actualScreen ) }
    // Se crea el objeto que habilita el snackbar en el scaffold
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        // El topBar se personliza de acuerdo al screen seleccionado
        topBar = {
            Text(
                text = actualScreen.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(24.dp, 32.dp)
            )
        },
        // El bottomBar se personliza de acuerdo al screen seleccionado
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationBar() {
                    NavigationBarItem(
                        selected = actualScreen == ScreenEnum.COUNTER,
                        onClick = { actualScreen = screenViewModel.changeScreen(ScreenEnum.COUNTER) },
                        icon = { Icon(ScreenEnum.COUNTER.icon, contentDescription = ScreenEnum.COUNTER.title) },
                        label = { Text(ScreenEnum.COUNTER.title) }
                    )
                    NavigationBarItem(
                        selected = actualScreen == ScreenEnum.REGISTRY,
                        onClick = { actualScreen = screenViewModel.changeScreen(ScreenEnum.REGISTRY) },
                        icon = { Icon(ScreenEnum.REGISTRY.icon, contentDescription = ScreenEnum.REGISTRY.title) },
                        label = { Text(ScreenEnum.REGISTRY.title) }
                    )
                    NavigationBarItem(
                        selected = actualScreen == ScreenEnum.LIST,
                        onClick = { actualScreen = screenViewModel.changeScreen(ScreenEnum.LIST) },
                        icon = { Icon(ScreenEnum.LIST.icon, contentDescription = ScreenEnum.LIST.title) },
                        label = { Text(ScreenEnum.LIST.title) }
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        // Se selecciona el componible dependiendo de la pantalla
        when (actualScreen) {
            ScreenEnum.COUNTER -> PeopleCounter(
                snackbarHostState,
                modifier = Modifier.padding(innerPadding)
            )
            ScreenEnum.REGISTRY -> PeopleRegistry(
                snackbarHostState,
                modifier = Modifier.padding(innerPadding)
            )
            ScreenEnum.LIST -> PeopleHistory(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}