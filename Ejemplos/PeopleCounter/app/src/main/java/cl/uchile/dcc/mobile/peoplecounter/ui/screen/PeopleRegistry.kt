package cl.uchile.dcc.mobile.peoplecounter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.peoplecounter.ui.component.InputTextField
import cl.uchile.dcc.mobile.peoplecounter.ui.component.PeopleCard
import cl.uchile.dcc.mobile.peoplecounter.ui.component.SubmitButton
import cl.uchile.dcc.mobile.peoplecounter.viewmodel.RegistryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PeopleRegistry(
    modifier: Modifier = Modifier.Companion,
    viewModel: RegistryViewModel = viewModel()
) {
    // Se definen las variables observables de la pantalla
    val personas by remember { mutableStateOf( viewModel.personas ) }
    var nombre by remember { mutableStateOf( viewModel.nombre ) }

    // Se define el layout de la pantalla
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
    ) {
        // Se define el input de texto
        OutlinedTextField(
            value = nombre,
            onValueChange = { it ->
                nombre = it
                viewModel.onChangeNombre(nombre)
            },
            label = {
                Text(text = "Ingrese el nombre del asistente")
            },
            isError = viewModel.errorNombre != null,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        // Se define el mensaje de error
        viewModel.errorNombre?.let { mensaje ->
            Text(
                text = mensaje,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        // Se define el boton de registro
        SubmitButton(
            "Registrar",
            enabled = viewModel.isValidNombre,
            callBack = {
                viewModel.addPerson(nombre)
                nombre = ""
            }
        )
        // Se define el listado de personas registradas
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            content = {
                items(personas) { persona ->
                    PeopleCard(persona)
                }
            }
        )
    }
}
