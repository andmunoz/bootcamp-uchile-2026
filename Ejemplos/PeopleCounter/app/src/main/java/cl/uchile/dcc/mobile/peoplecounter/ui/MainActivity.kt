package cl.uchile.dcc.mobile.peoplecounter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.peoplecounter.ui.screen.ScreenEnum
import cl.uchile.dcc.mobile.peoplecounter.ui.screen.PeopleCounter
import cl.uchile.dcc.mobile.peoplecounter.ui.screen.PeopleRegistry
import cl.uchile.dcc.mobile.peoplecounter.ui.theme.PeopleCounterTheme
import cl.uchile.dcc.mobile.peoplecounter.viewmodel.MainScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Se instancia el viewModel que tiene el estado de la pantalla
        val viewModel = MainScreenViewModel()
        setContent {
            PeopleCounterTheme {
                Scaffold(
                    // El topBar se personliza de acuerdo al screen seleccionado
                    topBar = {
                        Text(
                            text = viewModel.actualScreen.title,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(24.dp, 32.dp)
                        )
                    },
                    // El bottomBar se personliza de acuerdo al screen seleccionado
                    bottomBar = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 16.dp, 16.dp, 48.dp)
                        ) {
                            BottomAppBar(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                NavigationBar() {
                                    NavigationBarItem(
                                        selected = viewModel.actualScreen == ScreenEnum.COUNTER,
                                        onClick = { viewModel.changeToCounter() },
                                        icon = { Icon(Icons.Filled.Add, contentDescription = ScreenEnum.COUNTER.title) },
                                        label = { Text(ScreenEnum.COUNTER.title) }
                                    )
                                    NavigationBarItem(
                                        selected = viewModel.actualScreen == ScreenEnum.REGISTRY,
                                        onClick = { viewModel.changeToRegistry() },
                                        icon = { Icon(Icons.Filled.List, contentDescription = ScreenEnum.REGISTRY.title) },
                                        label = { Text(ScreenEnum.REGISTRY.title) }
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    // Se selecciona el componible dependiendo de la pantalla
                    when (viewModel.actualScreen) {
                        ScreenEnum.COUNTER -> PeopleCounter(
                            modifier = Modifier.padding(innerPadding)
                        )
                        ScreenEnum.REGISTRY -> PeopleRegistry(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
