package cl.uchile.dcc.mobile.foodregistry.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.uchile.dcc.mobile.foodregistry.viewmodel.FoodRegistryViewModel

@Composable
fun RegistryScreen(
    viewModel: FoodRegistryViewModel = viewModel(),
    onNavigate: (String) -> Unit = {}
) {
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
                text = "Agregar Comida",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Text(text = "Borrar")
            }
            Spacer( modifier = Modifier.padding(horizontal = 8.dp) )
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Agregar")
            }
        }
    }
}