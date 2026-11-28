package cl.uchile.dcc.mobile.mynotes2.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.window.core.layout.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.mynotes2.data.Note
import cl.uchile.dcc.mobile.mynotes2.ui.components.GetLocationPermission
import cl.uchile.dcc.mobile.mynotes2.ui.components.NoteCreateForm
import cl.uchile.dcc.mobile.mynotes2.ui.components.NoteDetail
import cl.uchile.dcc.mobile.mynotes2.ui.components.NoteList
import cl.uchile.dcc.mobile.mynotes2.viewmodel.NoteScreenMode
import cl.uchile.dcc.mobile.mynotes2.viewmodel.NoteViewModel

@Composable
fun NoteScreen(
    notes: List<Note>,
    viewModel: NoteViewModel
) {
    // Calculamos si el tamaño de pantalla corresponde a un teléfono o no
    val windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfoV2().windowSizeClass
    val isMobileSize = !(
            windowSizeClass.isWidthAtLeastBreakpoint(
                WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND   // 840.dp
            ) ||
            windowSizeClass.isWidthAtLeastBreakpoint(
                WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND     // 600.dp
            )
    )
    val leftSize = if (!isMobileSize) 0.3f else 1f
    val rightSize = if (!isMobileSize) 0.7f else 1f

    // Definimos una variable que nos permita escuchar los eventos a reportar en el snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    // Preguntamos si tiene permisos de acceso a la geolocalización
    var hasLocationPermission = false
    GetLocationPermission(
        onLocationGranted = {
            hasLocationPermission = true
        }
    )

    // Definimos la pantalla actual
    val currentScreen by viewModel.currentScreen.collectAsState()

    // Construimos la pantalla
    Scaffold(
        snackbarHost = {
            // Cuando tenemos mensaje que mostrar, lo mostramoss
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    // El título cambia dependiendo del modo de pantalla
                    val title = when (currentScreen) {
                        is NoteScreenMode.List -> "Mis Notas"
                        is NoteScreenMode.Create -> "Nueva Nota"
                        is NoteScreenMode.Detail -> "Nota: ${(currentScreen as NoteScreenMode.Detail).note.title}"
                    }
                    Text(title)
                },
                navigationIcon = {
                    // Aparecerá el ícono volver si es que aplica al tipo y modo de pantalla
                    if (isMobileSize && currentScreen !is NoteScreenMode.List) {
                        IconButton(onClick = { viewModel.goBack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            // Incorporamos el botón flotante en caso de que no estemos en el formulario
            if (currentScreen is NoteScreenMode.List || !isMobileSize && currentScreen is NoteScreenMode.Detail) {
                FloatingActionButton(onClick = { viewModel.navigateTo(NoteScreenMode.Create) }) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar Nota")
                }
            }
        }
    ) { innerPadding ->
        // Pintamo la pantalla dentro de los márgenes del dispositivo.
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // La lista de notas se muestra siempre cuando no es teléfono, si no se muestra cuando
            // estamos en modo List
            if (isMobileSize && currentScreen is NoteScreenMode.List || !isMobileSize) {
                Box(
                    modifier = Modifier
                        .padding(8.dp, 4.dp)
                        .weight(leftSize)
                ){
                    NoteList(
                        notes = notes,
                        onNoteClick = { note ->
                            viewModel.navigateTo(NoteScreenMode.Detail(note))
                        }
                    )
                }
            }

            // A la derecha de la lista cuando no es teléfono se muestra siempre algo, si no, solo
            // se debe mostrar si no estamos en modo List
            if (isMobileSize && currentScreen !is NoteScreenMode.List || !isMobileSize) {
                Box(
                    modifier = Modifier
                        .padding(8.dp, 4.dp)
                        .weight(rightSize)
                ) {
                    when (val mode = currentScreen) {
                        is NoteScreenMode.Create -> {
                            // Si estamos en modo Create, mostramos el formulario
                            NoteCreateForm(
                                onSaveNote = { title, content, picturePath ->
                                    viewModel.addNote(
                                        title,
                                        content,
                                        hasLocationPermission,
                                        picturePath
                                    )
                                    viewModel.navigateTo(NoteScreenMode.List)
                                },
                                onCancelNote = {
                                    viewModel.goBack()
                                }
                            )
                        }
                        is NoteScreenMode.Detail -> {
                            // Si estamos en modo Detail, mostramos el detalle de la nota actual
                            NoteDetail(
                                mode.note,
                                onShareNote = {
                                    viewModel.shareNote(mode.note)
                                }
                            )
                        }
                        is NoteScreenMode.List -> {
                            // Si no estamos ni en modo List, solo mostramos un mensaje.
                            // Este solo aparece si no estamos en un teléfono.
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("Selecciona una nota o crea una nueva", color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
}
