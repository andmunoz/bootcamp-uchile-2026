package cl.uchile.dcc.mobile.mynotes2.data.share

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import cl.uchile.dcc.mobile.mynotes2.data.Note
import java.io.File

class AndroidShareManager(private val context: Context) : ShareManager {
    override fun shareNote(note: Note) {
        var uri: Uri? = null
        val sendIntent: Intent
        if (note.picturePath != null) {
            val file = File(note.picturePath)
            uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
            sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_SUBJECT, note.title)
                putExtra(Intent.EXTRA_TEXT, "${note.title}\n\n${note.content}")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
        } else {
            sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, note.title)
                putExtra(Intent.EXTRA_TEXT, "${note.title}\n\n${note.content}")
            }
        }

        val shareIntent = Intent.createChooser(sendIntent, "Compartir nota").apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        context.startActivity(shareIntent)
    }
}