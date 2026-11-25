package cl.uchile.dcc.mobile.mynotes2.viewmodel

import cl.uchile.dcc.mobile.mynotes2.data.Note

sealed interface NoteScreenMode{
    data object List : NoteScreenMode
    data object Create : NoteScreenMode
    data class Detail(val note: Note) : NoteScreenMode
}
