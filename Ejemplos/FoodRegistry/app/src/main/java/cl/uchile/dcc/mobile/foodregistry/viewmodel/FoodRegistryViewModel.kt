package cl.uchile.dcc.mobile.foodregistry.viewmodel

import androidx.lifecycle.ViewModel
import cl.uchile.dcc.mobile.foodregistry.ui.screens.ScreenRoutes
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryEventState
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryFormState
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FoodRegistryViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(FoodRegistryScreenState(
        FoodRegistryFormState(),
        FoodRegistryEventState.Empty))
    val uiState: StateFlow<FoodRegistryScreenState> = _uiState

    fun updateFormState(formState: FoodRegistryFormState) {
        _uiState.update {
            it.copy(formState = formState)
        }
    }

    fun updateEventState(eventState: FoodRegistryEventState) {
        _uiState.update {
            it.copy(eventState = eventState)
        }
    }

    private val _currentRoute = MutableStateFlow(ScreenRoutes.OVERVIEW)
    val currentRoute: StateFlow<ScreenRoutes> = _currentRoute

    private val _navStack = MutableStateFlow(listOf(ScreenRoutes.OVERVIEW))
    val navStack: StateFlow<List<ScreenRoutes>> = _navStack.asStateFlow()

    fun navigateTo(route: String) {
        val screenRoute = ScreenRoutes.values().find { it.route == route } ?: ScreenRoutes.OVERVIEW
        _navStack.update { it + screenRoute }
        _currentRoute.update { screenRoute }
    }

    fun goBack() {
        if (_navStack.value.size <= 1) return
        _navStack.update { it.dropLast(1) }
        _currentRoute.update { _navStack.value.last() }
    }
}