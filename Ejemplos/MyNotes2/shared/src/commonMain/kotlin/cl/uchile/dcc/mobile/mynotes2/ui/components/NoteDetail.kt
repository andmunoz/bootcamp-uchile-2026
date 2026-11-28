package cl.uchile.dcc.mobile.mynotes2.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.mynotes2.data.Note
import cl.uchile.dcc.mobile.mynotes2.data.util.PictureSize
import cl.uchile.dcc.mobile.mynotes2.data.util.getLocalDateTimeFormat
import java.util.Date

@Composable
fun NoteDetail(
    note: Note,
    onShareNote: (Note) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(note.colorHex)
        ),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            IconButton(
                onClick = {
                    onShareNote(note)
                }
            ) {
                Icon(Icons.Default.Share, contentDescription = "Compartir nota")
            }
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
                    size = PictureSize.NORMALSIZE.size
                )
            }
            Text(
                text = note.created.getLocalDateTimeFormat(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}