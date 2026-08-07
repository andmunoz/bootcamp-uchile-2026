package cl.uchile.dcc.mobile.peoplecounter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.uchile.dcc.mobile.peoplecounter.model.PersonRegistry
import java.util.Date

class RegistryViewModel : ViewModel() {
    // Lista de personas registradas (usando mutableStateListOf para reactividad)
    val personas = mutableStateListOf<PersonRegistry>()

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

    // Agrega una persona a la lista
    fun addPerson(nombre: String) {
        // Obtener la fecha
        val date = Date()
        val fecha = date.toString().substring(0, 10)
        val hora = date.toString().substring(11, 16)

        // Formateamos el nombre limpio
        val nombreFormateado = formatNombre(nombre)

        // Crear la persona
        val persona = PersonRegistry(nombreFormateado, edad = edad, genero = genero, fecha = fecha, hora = hora)

        // Agregar al principio para ver el último registro
        personas.add(0, persona)
        personas.sortByDescending { it.edad }
    }

    // Nombres de los campos a guardar temporalmente
    var nombre by mutableStateOf("")
        private set
    var edad by mutableIntStateOf(0)
        private set
    var genero by mutableStateOf("")
        private set

    // Mensaje de error para los campos de texto
    var errorNombre: String? by mutableStateOf(null)
        private set
    var errorEdad: String? by mutableStateOf(null)
        private set
    var errorGenero: String? by mutableStateOf(null)
        private set

    // Valida que el nombre tenga el formato correcto
    fun updateNombre(nombre: String): String {
        this.nombre = nombre

        // Validamos las condiciones para ingresar un nombre
        errorNombre =
            if (nombre.isEmpty() || nombre.isBlank())
                "El nombre no puede estar vacío"
            else if (nombre.length < 3)
                "El nombre debe tener al menos 3 caracteres"
            else if (nombre.length > 30)
                "El nombre no puede tener más de 30 caracteres"
            else null

        // Si escribo más del largo, entonces solo muestro los primeros
        if (nombre.length > 30)
            this.nombre = nombre.substring(0, 30)

        return this.nombre
    }

    fun deleteNombre(): String {
        this.nombre = ""
        return this.nombre
    }

    fun updateEdad(edad: String): Int {
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

    // Estados de error
    val isValidForm: Boolean
        get() = errorNombre == null && errorEdad == null && errorGenero == null
}
