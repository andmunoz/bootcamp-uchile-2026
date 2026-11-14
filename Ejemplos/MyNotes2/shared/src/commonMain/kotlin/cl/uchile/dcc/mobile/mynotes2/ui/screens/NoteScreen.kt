package cl.uchile.dcc.mobile.mynotes2.ui.screens

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
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.window.core.layout.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cl.uchile.dcc.mobile.mynotes2.data.Note
import cl.uchile.dcc.mobile.mynotes2.ui.components.GetLocationPermission
import cl.uchile.dcc.mobile.mynotes2.ui.components.NoteDetail
import cl.uchile.dcc.mobile.mynotes2.ui.components.NoteList
import cl.uchile.dcc.mobile.mynotes2.ui.components.rememberCameraHandler
import cl.uchile.dcc.mobile.mynotes2.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    viewModel: NoteViewModel
) {
    val windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfoV2().windowSizeClass
    val isNotCompact = (
            windowSizeClass.isWidthAtLeastBreakpoint(
                WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND // 840.dp
            ) ||
            windowSizeClass.isWidthAtLeastBreakpoint(
                WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND // 600.dp
            )
    )

    val cameraHandler = rememberCameraHandler()
    var hasLocationPermission = false
    GetLocationPermission(
        onLocationGranted = {
            hasLocationPermission = true
        }
    )

    val selectedNote by viewModel.selectedNote.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    val title = if (selectedNote != null && !isNotCompact)
                        "Nota: ${selectedNote!!.title}"
                    else
                        "Mis Notas"
                    Text(title)
                },
                navigationIcon = {
                    // Si estamos en Compact y hay una nota seleccionada, mostramos botón "Volver"
                    if (selectedNote != null && !isNotCompact) {
                        IconButton(onClick = { viewModel.selectNote(null) }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            // El botón de agregar ahora es un FAB flotante, más moderno
            FloatingActionButton(
                onClick = {
                    cameraHandler.takePicture(
                        onSuccess = { path ->
                            viewModel.addNote(
                                "Nueva Nota",
                                "Contenido de prueba",
                                hasLocationPermission,
                                path
                            )
                        },
                        onError = { error ->
                            viewModel.addNote(
                                "Nueva Nota",
                                "Contenido de prueba",
                                hasLocationPermission,
                                null
                            )
                        }
                    )
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Nota")
            }
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (selectedNote == null || isNotCompact) {
                NoteList(
                    notes = notes,
                    viewModel = viewModel
                )
            }
            if (selectedNote != null || isNotCompact) {
                NoteDetail(selectedNote)
            }
        }
    }
}
