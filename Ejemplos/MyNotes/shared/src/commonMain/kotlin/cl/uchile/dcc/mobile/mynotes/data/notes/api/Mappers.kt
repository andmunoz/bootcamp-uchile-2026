package cl.uchile.dcc.mobile.mynotes.data.notes.api

import cl.uchile.dcc.mobile.mynotes.data.notes.database.NoteDAO

fun NoteDTO.toNote(): NoteDAO {
    return NoteDAO(
            id = this.id,
            title = this.title,
            content = this.content,
            colorHex = this.colorHex,
            created = this.created
        )
}

fun NoteDAO.toNoteDTO(): NoteDTO {
    return NoteDTO(
            id = this.id!!,
            title = this.title,
            content = this.content,
            colorHex = this.colorHex,
            created = this.created
        )
}