package cl.uchile.dcc.mobile.foodregistry.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.uchile.dcc.mobile.foodregistry.viewmodel.FoodRegistryViewModel

@Composable
fun HistoryScreen(
    viewModel: FoodRegistryViewModel = viewModel(),
    onNavigate: (String) -> Unit = {}
) {
    viewModel.getFoodRegistries()
    val itemList by viewModel.foodRegistryList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Historial de Comidas",
                style = MaterialTheme.typography.headlineMedium,
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            content = {
                items(itemList) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Fecha: ${viewModel.recodeDate(it.fecha)}",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 8.dp)
                        ) {
                            Text(
                                text = "${it.tipoId}:",
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(end = 2.dp)
                            )
                            Text(
                                text = "${it.descripcion}",
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(start = 2.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        {
                            Text(
                                text = "Calorias: ${it.calorias} kcal",
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(end = 2.dp)
                            )
                            Text(
                                text = "Carbohidratos: ${it.carbohidratos} grs",
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(start = 2.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}