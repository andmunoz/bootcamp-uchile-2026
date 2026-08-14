package cl.uchile.dcc.mobile.foodregistry.viewmodel

import androidx.lifecycle.ViewModel
import cl.uchile.dcc.mobile.foodregistry.ui.screens.ScreenRoutes
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryEventState
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryFormState
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    fun navigateTo(route: String) {
        _currentRoute.update {
            when (route) {
                ScreenRoutes.OVERVIEW.route -> ScreenRoutes.OVERVIEW
                ScreenRoutes.REGISTRY.route -> ScreenRoutes.REGISTRY
                ScreenRoutes.HISTORY.route -> ScreenRoutes.HISTORY
                ScreenRoutes.FOODS.route -> ScreenRoutes.FOODS
                ScreenRoutes.SETTINGS.route -> ScreenRoutes.SETTINGS
                else -> ScreenRoutes.OVERVIEW
            }
        }
    }
}