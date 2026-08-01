package cl.uchile.dcc.mobile.peoplecounter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.uchile.dcc.mobile.peoplecounter.model.PersonRegistry
import java.util.Date

class RegistryViewModel : ViewModel() {
    // Lista de personas registradas (usando mutableStateListOf para reactividad)
    val personas = mutableStateListOf<PersonRegistry>()

    // Agrega una persona a la lista
    fun addPerson(nombre: String) {
        val date = Date()
        val fecha = date.toString().substring(0, 10)
        val hora = date.toString().substring(11, 16)
        val persona = PersonRegistry(nombre, fecha, hora)
        personas.add(0, persona) // Agregar al principio para ver el último registro
    }

    // Nombre en el campo para guardar
    var nombre by mutableStateOf("")
        private set

    // Mensaje de error para el nombre
    var errorNombre: String? by mutableStateOf(null)
        private set

    // Valida que el nombre tenga el formato correcto
    fun onChangeNombre(nombre: String) {
        this.nombre = nombre

        errorNombre =
            if (nombre.isEmpty() || nombre.isBlank())
                "El nombre no puede estar vacío"
            else if (nombre.length < 3)
                "El nombre debe tener al menos 3 caracteres"
            else null
    }

    // Estado con error
    val isValidNombre: Boolean
        get() = nombre.isNotEmpty() && nombre.isNotBlank() && errorNombre == null
}
