package cl.uchile.dcc.mobile.peoplecounter.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.uchile.dcc.mobile.peoplecounter.ui.component.PeopleCard
import cl.uchile.dcc.mobile.peoplecounter.viewmodel.RegistryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.uchile.dcc.mobile.peoplecounter.model.PeopleScreenState

@Composable
fun PeopleHistory(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    viewModel: RegistryViewModel = viewModel()
) {
    val screenState by viewModel.state.collectAsStateWithLifecycle()
    val state = screenState.people

    LaunchedEffect(state) {
        if (state is PeopleScreenState.Loading) {
            snackBarHostState.showSnackbar("Cargando personas...")
            viewModel.loadPeople()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp, 0.dp)
    ) {
        when (state) {
            is PeopleScreenState.Loading -> {
                Text(
                    text = "Cargando...",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }
            is PeopleScreenState.Error -> {
                LaunchedEffect(state) {
                    snackBarHostState.showSnackbar("Error al cargar personas")
                }
            }
            is PeopleScreenState.Saving -> {
                LaunchedEffect(state) {
                    snackBarHostState.showSnackbar("Guardando persona")
                }
            }
            is PeopleScreenState.Empty -> {
                Text(
                    text = "No hay personas registradas",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }
            else -> {
                Text(
                    text = "Personas registradas: ${viewModel.getPersonas().size}",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            content = {
                items(viewModel.getPersonas()) { persona ->
                    PeopleCard(persona)
                }
            }
        )
    }
}
