package cl.uchile.dcc.mobile.peoplecounter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {
    var actualScreen by mutableStateOf("COUNTER")
        private set

    fun changeToCounter() {
        actualScreen = "COUNTER"
    }

    fun changeToRegistry() {
        actualScreen = "REGISTRY"
    }
}


