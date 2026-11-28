package cl.uchile.dcc.mobile.mynotes2.data.share

import cl.uchile.dcc.mobile.mynotes2.data.Note

interface ShareManager {
    fun shareNote(note: Note)
}