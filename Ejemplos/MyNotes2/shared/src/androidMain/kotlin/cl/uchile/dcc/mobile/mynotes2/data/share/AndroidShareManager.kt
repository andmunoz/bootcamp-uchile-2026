package cl.uchile.dcc.mobile.mynotes2.data.share

import android.content.Context
import android.content.Intent
import cl.uchile.dcc.mobile.mynotes2.data.Note

class AndroidShareManager(private val context: Context) : ShareManager {
    override fun shareTextNote(note: Note) {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, note.title)
            // Unimos el título y el contenido para enviarlo
            putExtra(Intent.EXTRA_TEXT, "${note.title}\n\n${note.content}")
        }

        val shareIntent = Intent.createChooser(sendIntent, "Compartir nota").apply {
            // Importante para iniciar actividades desde fuera de una Activity (ej. desde un ViewModel)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        context.startActivity(shareIntent)
    }

    override fun shareImageNote(note: Note) {
        TODO("Not yet implemented")
    }
}