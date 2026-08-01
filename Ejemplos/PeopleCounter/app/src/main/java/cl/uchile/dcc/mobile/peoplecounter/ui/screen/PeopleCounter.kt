package cl.uchile.dcc.mobile.peoplecounter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.uchile.dcc.mobile.peoplecounter.ui.component.DigitCounter
import cl.uchile.dcc.mobile.peoplecounter.ui.component.SubmitButton
import cl.uchile.dcc.mobile.peoplecounter.viewmodel.CounterViewModel

@Composable
fun PeopleCounter(
    viewModel: CounterViewModel = viewModel(),
    modifier: Modifier = Modifier.Companion
) {
    // Se define la variable observable
    var contador by remember { mutableStateOf(viewModel.contador ) }

    // Se define el layout
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
    ) {
        // Se define el layout de la fila
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Se define el layout de los digitos
            var resto = 0
            DigitCounter(contador / 100)
            resto = contador % 100
            DigitCounter(resto / 10)
            resto = resto % 10
            DigitCounter(resto)
        }
        // Se define el boton
        SubmitButton(
            "Contar",
            enabled = viewModel.isValidContador,
            callBack = {
                contador++
                viewModel.raiseContador()
            }
        )
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