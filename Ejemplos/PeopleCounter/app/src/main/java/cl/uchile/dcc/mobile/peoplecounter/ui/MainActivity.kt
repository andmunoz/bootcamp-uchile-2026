package cl.uchile.dcc.mobile.peoplecounter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.peoplecounter.ui.component.DigitCounter
import cl.uchile.dcc.mobile.peoplecounter.ui.component.MenuButton
import cl.uchile.dcc.mobile.peoplecounter.ui.component.SubmitButton
import cl.uchile.dcc.mobile.peoplecounter.ui.screen.PeopleCounter
import cl.uchile.dcc.mobile.peoplecounter.ui.screen.PeopleRegistry
import cl.uchile.dcc.mobile.peoplecounter.ui.theme.PeopleCounterTheme
import cl.uchile.dcc.mobile.peoplecounter.viewmodel.MainScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Se usé la pantalla del dispositivo completa
        val viewModel = MainScreenViewModel()
        setContent {
            PeopleCounterTheme {
                Scaffold(
                    topBar = {
                        Text(
                            text = "THEATRICAL TOOLS",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(24.dp, 32.dp)
                        )
                    },
                    bottomBar = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 16.dp, 16.dp, 48.dp)
                        ) {
                            MenuButton(
                                "Contador",
                                callBack = { viewModel.changeToCounter() }
                            )
                            MenuButton(
                                "Registro",
                                callBack = { viewModel.changeToRegistry() }
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    if (viewModel.actualScreen == "REGISTRY") {
                        PeopleRegistry(
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else if (viewModel.actualScreen == "COUNTER") {
                        PeopleCounter(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
