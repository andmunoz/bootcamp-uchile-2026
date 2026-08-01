package cl.uchile.dcc.mobile.peoplecounter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    // Resguardo del contador
    var contador by mutableStateOf(0)
        private set

    // Almacenaje del contador cuando cambia
    fun onChangeContador(contador: Int) {
        this.contador = contador
    }
}