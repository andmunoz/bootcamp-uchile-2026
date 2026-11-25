package cl.uchile.dcc.mobile.mynotes2.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.window.core.layout.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.mynotes2.data.Note
import cl.uchile.dcc.mobile.mynotes2.ui.components.GetLocationPermission
import cl.uchile.dcc.mobile.mynotes2.ui.components.NoteCreateForm
import cl.uchile.dcc.mobile.mynotes2.ui.components.NoteDetail
import cl.uchile.dcc.mobile.mynotes2.ui.components.NoteList
import cl.uchile.dcc.mobile.mynotes2.viewmodel.NoteViewModel

@Composable
fun NoteScreen(
    notes: List<Note>,
    viewModel: NoteViewModel
) {
    val windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfoV2().windowSizeClass
    val isNotCompact = (
            windowSizeClass.isWidthAtLeastBreakpoint(
                WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND   // 840.dp
            ) ||
            windowSizeClass.isWidthAtLeastBreakpoint(
                WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND     // 600.dp
            )
    )
    var leftSize = 1f
    var rightSize = 1f
    if (isNotCompact) {
        leftSize = 0.3f
        rightSize = 0.7f
    }

    var hasLocationPermission = false
    GetLocationPermission(
        onLocationGranted = {
            hasLocationPermission = true
        }
    )

    var newNote by remember { mutableStateOf(false) }
    val selectedNote by viewModel.selectedNote.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    val title = if (selectedNote != null && !isNotCompact)
                        "Nota: ${selectedNote!!.title}"
                    else if (newNote && !isNotCompact)
                        "Nueva Nota"
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
            FloatingActionButton(
                onClick = {
                    newNote = true
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
            Box(
                modifier = Modifier
                    .padding(8.dp, 0.dp)
                    .weight(leftSize)
            ){
                if ((selectedNote == null && !newNote) || isNotCompact) {
                    NoteList(
                        notes = notes,
                        viewModel = viewModel
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(8.dp, 0.dp)
                    .weight(rightSize)
            ){
                if (newNote) {
                    viewModel.selectNote(null)
                    NoteCreateForm { title, content, picturePath ->
                        viewModel.addNote(
                            title,
                            content,
                            hasLocationPermission,
                            picturePath
                        )
                        newNote = false
                    }
                } else if (selectedNote != null || isNotCompact) {
                    NoteDetail(selectedNote)
                }
            }
        }
    }
}
