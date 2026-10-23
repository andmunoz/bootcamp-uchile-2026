package cl.uchile.dcc.mobile.mynotes.data.notes.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class NoteAPI(
    private val client: HttpClient,
) {
    companion object {
        val baseUrl = "httts://api.miappnotes.cl/v1"
    }

    // GET notes
    suspend fun fetchNotes(): List<NoteDTO> {
        return client.get("$baseUrl/notes").body()
    }

    // POST a note
    suspend fun addNote(note: NoteDTO): NoteDTO {
        return client.post("$baseUrl/notes") {
            contentType(ContentType.Application.Json)
            setBody(note)
        }.body()
    }
}