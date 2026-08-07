package cl.uchile.dcc.mobile.peoplecounter.ui.screen

import androidx.collection.mutableIntSetOf
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.peoplecounter.ui.component.PeopleCard
import cl.uchile.dcc.mobile.peoplecounter.ui.component.SubmitButton
import cl.uchile.dcc.mobile.peoplecounter.viewmodel.RegistryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun PeopleHistory(
    modifier: Modifier = Modifier,
    viewModel: RegistryViewModel = viewModel()
) {
    // Se definen las variables observables de la pantalla
    val personas = remember { viewModel.personas }
    val contador = remember { mutableIntStateOf(viewModel.personas.size) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp, 0.dp)
    ) {
        Text(
            text = "Cantidad de Personas: ${contador.intValue}",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .align(Alignment.End)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            content = {
                items(personas) { persona ->
                    PeopleCard(persona)
                }
            }
        )
    }
}
