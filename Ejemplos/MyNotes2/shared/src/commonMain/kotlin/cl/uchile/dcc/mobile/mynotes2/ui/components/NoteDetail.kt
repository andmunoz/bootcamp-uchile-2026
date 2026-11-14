package cl.uchile.dcc.mobile.mynotes2.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.mynotes2.data.Note
import java.util.Date

@Composable
fun NoteDetail(
    note: Note?
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(note?.colorHex ?: 0xFFF5F5F5) // Usa el valor que viene de la base de datos
        ),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            if (note != null) {
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
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    PictureHandler(
                        picture = note.picturePath,
                        size = 180.dp
                    )
                }
                Text(
                    text = "${Date(note.created)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Text("Seleccione una nota para ver el detalle")
            }
        }
    }
}