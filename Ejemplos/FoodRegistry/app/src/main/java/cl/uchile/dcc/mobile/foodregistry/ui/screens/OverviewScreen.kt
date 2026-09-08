package cl.uchile.dcc.mobile.foodregistry.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.uchile.dcc.mobile.foodregistry.viewmodel.FoodRegistryViewModel

@Composable
fun OverviewScreen(
    viewModel: FoodRegistryViewModel = viewModel(),
    onNavigate: (String) -> Unit = {}
) {
    val theme = viewModel.appTheme.collectAsState().value
    val username = viewModel.username.collectAsState().value
    var name by remember { mutableStateOf("") }
    val foodRegistryList by viewModel.foodRegistryList.collectAsState()
    val overview = viewModel.getOverview(foodRegistryList)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Resumen de Alimentación",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        if (username.isNotEmpty() && !username.equals("Anónimo")) {
            Text(
                text = "Hola $username",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
        }
        else {
            OutlinedTextField(
                value = name,
                onValueChange = { it ->
                    name = it
                },
                label = {
                    Text(
                        text = "Nombre de Usuario",
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Button(
                onClick = {
                    viewModel.setUsername(name)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Guardar",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        overview.forEach {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Fecha: ${viewModel.recodeDate(it.fecha)} (${it.registros} registros)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 2.dp, start = 8.dp, end = 8.dp)
                )
                Text(
                    text = "- Calorias: ${it.indicadores.calorias} kcal",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 8.dp)
                )
                Text(
                    text = "- Carbohidratos: ${it.indicadores.carbohidratos} grs",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .padding(top = 2.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                )
            }
        }
    }
}