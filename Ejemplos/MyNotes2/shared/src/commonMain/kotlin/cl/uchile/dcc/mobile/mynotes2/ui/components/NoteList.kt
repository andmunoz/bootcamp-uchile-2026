package cl.uchile.dcc.mobile.mynotes2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.mynotes2.data.Note
import cl.uchile.dcc.mobile.mynotes2.viewmodel.NoteViewModel

@Composable
fun NoteList(
    notes: List<Note>,
    viewModel: NoteViewModel
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(notes) { note ->
            NoteOverview(
                note = note,
                onClickNote = {
                    viewModel.selectNote(note)
                }
            )
        }
    }
}