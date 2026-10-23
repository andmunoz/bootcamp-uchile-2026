package cl.uchile.dcc.mobile.mynotes.data.notes.api

import cl.uchile.dcc.mobile.mynotes.data.notes.database.Note

fun NoteDTO.toNote(): Note {
    return Note(
            id = this.id,
            title = this.title,
            content = this.content,
            colorHex = this.colorHex,
            created = this.created
        )
}

fun Note.toNoteDTO(): NoteDTO {
    return NoteDTO(
            id = this.id!!,
            title = this.title,
            content = this.content,
            colorHex = this.colorHex,
            created = this.created
        )
}