package cl.uchile.dcc.mobile.peoplecounter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    // Resguardo del contador
    var contador by mutableIntStateOf(0)
        private set

    // Registro de error
    var errorContador: String? by mutableStateOf(null)
        private set

    // Almacenaje del contador cuando cambia
    fun raiseContador(): Int {
        if (contador < 999)
            contador++
        else
            errorContador = "El contador no puede superar 999"
        return contador
    }

    fun setContador(contador: Int): Int {
        this.contador = contador
        return contador
    }

    // Estado con error
    val isValidContador: Boolean
        get() = errorContador == null
}