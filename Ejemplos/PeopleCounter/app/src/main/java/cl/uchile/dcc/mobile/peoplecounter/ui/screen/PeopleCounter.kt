package cl.uchile.dcc.mobile.peoplecounter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.uchile.dcc.mobile.peoplecounter.ui.component.DigitCounter
import cl.uchile.dcc.mobile.peoplecounter.ui.component.ResetButton
import cl.uchile.dcc.mobile.peoplecounter.ui.component.SubmitButton
import cl.uchile.dcc.mobile.peoplecounter.viewmodel.CounterViewModel
import cl.uchile.dcc.mobile.peoplecounter.viewmodel.RegistryViewModel
import kotlinx.coroutines.launch

@Composable
fun PeopleCounter(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    viewModel: CounterViewModel = viewModel(),
    registryViewModel: RegistryViewModel = viewModel()
) {
    // Se define la variable observable
    var contador by remember { mutableIntStateOf(viewModel.contador) }
    // contador = viewModel.setContador(registryViewModel.personas.size)

    var scope = rememberCoroutineScope()

    // Se define el layout
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(16.dp, 0.dp)
        ) {
            // Se define el layout de la fila
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                // Se define el layout de los digitos
                var resto = 0
                DigitCounter(contador / 100)
                resto = contador % 100
                DigitCounter(resto / 10)
                resto %= 10
                DigitCounter(resto)
            }
        }

        // Se define la botonera
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
                            message = "¡Contador Reiniciado!",
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                    }
                    contador = viewModel.setContador(0)
                },
                icon = Icons.Filled.Clear
            )
            SubmitButton(
                "Contar",
                enabled = viewModel.isValidContador,
                callBack = {
                    contador = viewModel.raiseContador()
                },
                icon = Icons.Filled.AddCircle
            )
        }

        // Presentación del error
        viewModel.errorContador?.let { mensaje ->
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
}