package cl.uchile.dcc.mobile.mynotes2.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
fun NoteOverview(
    note: Note,
    onClickNote: () -> Unit
) {
    Card(
        onClick = {
            onClickNote()
        },
        colors = CardDefaults.cardColors(
            containerColor = Color(note.colorHex)
        ),
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
                    text = "${Date(note.created)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                PictureHandler(
                    picture = note.picturePath,
                    size = 60.dp
                )
            }
        }
    }
}