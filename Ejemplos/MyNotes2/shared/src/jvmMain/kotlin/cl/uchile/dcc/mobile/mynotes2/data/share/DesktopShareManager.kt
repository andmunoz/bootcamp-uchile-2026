package cl.uchile.dcc.mobile.mynotes2.data.share

import cl.uchile.dcc.mobile.mynotes2.data.Note
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

class DesktopShareManager: ShareManager {

    override fun shareTextNote(note: Note) {
        val textToCopy = "${note.title}\n\n${note.content}"
        val selection = StringSelection(textToCopy)

        // Copiamos al portapapeles del sistema
        Toolkit.getDefaultToolkit().systemClipboard.setContents(selection, selection)

        println("✅ Nota copiada al portapapeles. Lista para pegar.")
        // Aquí podrías mostrar un Snackbar en la UI para avisar al usuario.
    }

    override fun shareImageNote(note: Note) {
        TODO("Not yet implemented")
    }
}