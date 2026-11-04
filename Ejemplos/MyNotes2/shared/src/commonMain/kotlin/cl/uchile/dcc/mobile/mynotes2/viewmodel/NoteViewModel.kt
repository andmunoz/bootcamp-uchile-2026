package cl.uchile.dcc.mobile.mynotes2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.dcc.mobile.mynotes2.data.Note
import cl.uchile.dcc.mobile.mynotes2.data.NotesRepository
import cl.uchile.dcc.mobile.mynotes2.data.location.LocationData
import cl.uchile.dcc.mobile.mynotes2.data.location.LocationProvider
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel(
    private val notesRepository: NotesRepository,
    private val locationProvider: LocationProvider
): ViewModel() {
    val uiState: StateFlow<NoteUiState> = notesRepository.getNotes()
        .map { note ->
            NoteUiState.Success(note) as NoteUiState
        }
        .catch { error ->
            Logger.e(
                "Error al cargar las notas",
                error,
                "NoteViewModel"
            )
            emit(NoteUiState.Error(error.message ?: "Error desconocido"))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NoteUiState.Loading
        )

    fun addNote(title: String, content: String, locationPermission: Boolean, picturePath: String?) {
        viewModelScope.launch {
            var locationData: LocationData? = null
            if (locationPermission) {
                locationData = locationProvider.getCurrentLocation()
            }
            val note = Note(
                title = title,
                content = content,
                colorHex = 0L,
                location = locationData,
                picturePath = picturePath
            )
            notesRepository.addNote(note)
        }
    }

    fun updateNote(id: Int, title: String, content: String, colorHex: Long, picturePath: String?) {
        viewModelScope.launch {
            val originalNote: Note = notesRepository.getNote(id)
            val note = Note(
                id = id,
                title = title,
                content = content,
                colorHex = colorHex,
                location = originalNote.location,
                picturePath = picturePath,
                created = originalNote.created
            )
            notesRepository.addNote(note)
        }
    }

    fun removeNote(note: Note) {
        viewModelScope.launch {
            notesRepository.removeNote(note)
        }
    }
}