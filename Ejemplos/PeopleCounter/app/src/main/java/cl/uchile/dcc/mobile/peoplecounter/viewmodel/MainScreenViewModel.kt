package cl.uchile.dcc.mobile.peoplecounter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.uchile.dcc.mobile.peoplecounter.ui.screen.ScreenEnum

class MainScreenViewModel : ViewModel() {
    var actualScreen by mutableStateOf(ScreenEnum.COUNTER)
        private set

    fun changeToCounter() {
        actualScreen = ScreenEnum.COUNTER
    }

    fun changeToRegistry() {
        actualScreen = ScreenEnum.REGISTRY
    }
}


