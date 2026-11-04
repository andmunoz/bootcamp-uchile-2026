package cl.uchile.dcc.mobile.mynotes2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.mynotes2.data.Note
import cl.uchile.dcc.mobile.mynotes2.ui.components.PictureThumbnail

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp, 24.dp, 12.dp, 8.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(86F)
                .fillMaxWidth()
        ) {
            items(notes) { note ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .weight(3f)
                        ) {
                            Text(
                                text = note.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = note.content,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Ubicación: ${note.location?.latitude}, ${note.location?.longitude}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .weight(1f)
                        ) {
                            PictureThumbnail(note.picturePath)
                        }
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
                .padding(16.dp)
                .weight(2F)
        )
        Button(
            onClick = onAddNote,
            modifier = Modifier
                .fillMaxWidth()
                .weight(6F)
        ) {
            Text("Agregar Nota de Prueba")
        }
        Spacer(
            modifier = Modifier
                .padding(16.dp)
                .weight(6F)
        )
    }
}