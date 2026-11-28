package cl.uchile.dcc.mobile.mynotes2.data.share

import cl.uchile.dcc.mobile.mynotes2.data.Note
import java.awt.Desktop
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File

class DesktopShareManager: ShareManager {

    override fun shareNote(note: Note) {
        if (note.picturePath != null && Desktop.isDesktopSupported()) {
            val file = File(note.picturePath)
            Desktop.getDesktop().open(file)
        } else {
            val textToCopy = "${note.title}\n\n${note.content}"
            val selection = StringSelection(textToCopy)
            Toolkit.getDefaultToolkit().systemClipboard.setContents(selection, selection)
        }
    }
}