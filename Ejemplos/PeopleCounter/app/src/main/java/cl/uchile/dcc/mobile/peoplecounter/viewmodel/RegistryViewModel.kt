package cl.uchile.dcc.mobile.peoplecounter.viewmodel

import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.dcc.mobile.peoplecounter.model.PeopleScreenState
import cl.uchile.dcc.mobile.peoplecounter.model.PersonRegistry
import cl.uchile.dcc.mobile.peoplecounter.model.RegistryFormState
import cl.uchile.dcc.mobile.peoplecounter.model.RegistryUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class RegistryViewModel : ViewModel() {
    // Lista de personas registradas (usando mutableStateListOf para reactividad)
    private val _state = MutableStateFlow(RegistryUIState(
        registry = RegistryFormState(),
        people = PeopleScreenState.Loading
    ))
    val state: StateFlow<RegistryUIState> = _state.asStateFlow()

    fun formatNombre(nombre: String): String {
        // Separar en una LISTA los diferentes nombres que vienen separados de espacios en blanco
        val nombres = nombre.trim().split(" ")

        // Formatear cada nombre de la lista
        val nombreFormateado = nombres.map {
            it.trim().replaceFirstChar { it.uppercase() }
        }

        // Unir los nombres en una cadena
        return nombreFormateado.joinToString(" ")
    }

    fun getPersonas() : List<PersonRegistry> {
        val personas = when (val ui = state.value.people) {
            is PeopleScreenState.Success -> ui.people
            else -> emptyList()
        }
        return personas
    }

    // Agrega una persona a la lista
    fun addPerson() {
        // Formateamos el nombre limpio
        val nombreFormateado = formatNombre(_state.value.registry.nombre)
        if (nombreFormateado.isEmpty()) {
            _state.update {
                it.copy(
                    registry = it.registry.copy(error = "El nombre no puede estar vacío")
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    people = PeopleScreenState.Saving
                )
            }
            delay(2000)

            // Obtener la fecha
            val date = Date()
            val fecha = date.toString().substring(0, 10)
            val hora = date.toString().substring(11, 16)

            // Crear la persona
            val persona = PersonRegistry(
                nombreFormateado,
                edad = _state.value.registry.edad,
                genero = _state.value.registry.genero,
                fecha = fecha, hora = hora)

            // Agregar al principio para ver el último registro
            _state.update {
                val personas = getPersonas()
                val newList = listOf(persona) + personas
                it.copy(
                    people = PeopleScreenState.Success(
                        people = newList,
                        counter = newList.size
                    )
                )
            }
        }
    }

    fun loadPeople() {
        viewModelScope.launch {
            delay(2000)
            _state.update {
                it.copy(
                    people = PeopleScreenState.Empty
                )
            }
        }
    }

    // Valida que el nombre tenga el formato correcto
    fun updateNombre(nombre: String) {
        // Validamos las condiciones para ingresar un nombre
        _state.update {
            it.copy(
                registry = it.registry.copy(error =
                if (nombre.isEmpty() || nombre.isBlank())
                    "El nombre no puede estar vacío"
                else if (nombre.length < 3)
                    "El nombre debe tener al menos 3 caracteres"
                else if (nombre.length > 30)
                    "El nombre no puede tener más de 30 caracteres"
                else null )
            )
        }

        // Si escribo más del largo, entonces solo muestro los primeros
        if (nombre.length > 30)
            _state.update {
                it.copy(
                    registry = it.registry.copy(nombre = nombre.substring(0, 30))
                )
            }
        else
            _state.update {
                it.copy(
                    registry = it.registry.copy(nombre = nombre)
                )
            }
    }

    fun deleteNombre() {
        _state.update {
            it.copy(
                registry = it.registry.copy(nombre = "")
            )
        }
    }

/*     fun updateEdad(edad: String): Int {
        this.edad = edad.toIntOrNull() ?: 0

        errorEdad =
            if (edad.isEmpty() || edad.isBlank())
                "La edad no puede estar vacía"
            else if (edad.toIntOrNull() == null)
                "La edad debe ser un número"
            else if (this.edad < 0)
                "La edad no puede ser negativa"
            else if (this.edad > 120)
                "La edad no puede ser mayor a 120"
            else null

        return this.edad
    }

    fun deleteEdad(): Int {
        this.edad = 0
        return this.edad
    }

    fun updateGenero(genero: String): String {
        this.genero = genero

        errorGenero =
            if (genero.isEmpty() || genero.isBlank())
                "El género no puede estar vacío"
            else null

        return this.genero
    }

    fun deleteGenero(): String {
        this.genero = ""
        return this.genero
    }

    */
}
