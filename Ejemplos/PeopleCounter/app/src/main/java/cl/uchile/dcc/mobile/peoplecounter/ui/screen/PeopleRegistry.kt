package cl.uchile.dcc.mobile.peoplecounter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.uchile.dcc.mobile.peoplecounter.ui.component.SubmitButton
import cl.uchile.dcc.mobile.peoplecounter.viewmodel.RegistryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.uchile.dcc.mobile.peoplecounter.ui.component.ResetButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleRegistry(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    viewModel: RegistryViewModel = viewModel()
) {
    // Se definen las variables observables de la pantalla
    val screenState by viewModel.state.collectAsStateWithLifecycle()
    val state = screenState.registry

    // Se define el snackbarHostState
    val scope = rememberCoroutineScope()

    // Se define el layout de la pantalla
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
        ) {
            // Se define el input de texto para el nombre
            OutlinedTextField(
                value = state.nombre,
                onValueChange = { it ->
                    viewModel.updateNombre(it)
                },
                label = {
                    Text(text = "Ingrese el nombre del asistente")
                },
                isError = state.error != null,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            // Se define el mensaje de error
            state.error?.let { mensaje ->
                Text(
                    text = mensaje,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }

            // Se define el input de texto para la edad
/*             OutlinedTextField(
                value = edad.toString(),
                onValueChange = { it ->
                    edad = viewModel.updateEdad(it)
                },
                label = {
                    Text(text = "Ingrese la edad del asistente")
                },
                isError = viewModel.errorEdad != null,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            )
            // Se define el mensaje de error
            viewModel.errorEdad?.let { mensaje ->
                Text(
                    text = mensaje,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }

            // Se define el Dropdown para género
            val generos = listOf("Masculino", "Femenino", "Tránsgenero", "No Binario", "Otro")
            var expanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = genero,
                    onValueChange = {
                        genero = it
                    },
                    label = {
                        Text(text = "Ingrese el género del asistente")
                    },
                    isError = viewModel.errorGenero != null,
                    readOnly = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    generos.forEach { it ->
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                genero = viewModel.updateGenero(it)
                                expanded = false
                            }
                        )
                    }
                }
            }
            // Se define el mensaje de error
            viewModel.errorGenero?.let { mensaje ->
                Text(
                    text = mensaje,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }

        } */

        // Se define el boton de registro
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            ResetButton(
                "Borrar",
                enabled = true,
                callBack = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "¡Formulario Borrado!",
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                    }
                    viewModel.deleteNombre()
                },
                icon = Icons.Filled.Clear
            )
            SubmitButton(
                "Registrar",
                enabled = state.error == null && state.nombre.length > 0,
                callBack = {
                    viewModel.addPerson()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "¡Persona registrada!",
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                    }
                    viewModel.deleteNombre()
                },
                icon = Icons.Filled.SaveAlt
            )
        }
    }
}
