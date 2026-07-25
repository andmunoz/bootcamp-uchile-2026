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
import cl.uchile.dcc.mobile.peoplecounter.ui.theme.PeopleCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Se usé la pantalla del dispositivo completa
        setContent {
            PeopleCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PeopleCounter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PeopleCounter(modifier: Modifier = Modifier.Companion) {
    var nombre by remember { mutableStateOf("") }
    val personas: MutableList<String> = remember { mutableListOf() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
    ) {
        Text(
            text = "PEOPLE COUNTER",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        RowCounter()
        OutlinedTextField(
            value = nombre,
            onValueChange = { it ->
                nombre = it
            },
            label = { Text("Nombre del Asistente") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = {
                personas.add(nombre)
                nombre = ""
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Registrar",
                modifier = Modifier.padding(24.dp)
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            content = {
                items(personas) { persona ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = persona,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun RowCounter() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card() {
            Text(
                text = "0",
                modifier = Modifier.padding(32.dp, 16.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Card() {
            Text(
                text = "0",
                modifier = Modifier.padding(32.dp, 16.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Card() {
            Text(
                text = "0",
                modifier = Modifier.padding(32.dp, 16.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

/* @Preview(showBackground = true)
@Composable
fun PeopleCounterPreview() {
    PeopleCounterTheme {
        PeopleCounter()
    }
} */