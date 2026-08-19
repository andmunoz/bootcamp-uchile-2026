package cl.uchile.dcc.mobile.foodregistry.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.dcc.mobile.foodregistry.data.FoodRegistry
import cl.uchile.dcc.mobile.foodregistry.ui.screens.ScreenRoutes
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryEventState
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryFormState
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FoodRegistryViewModel(
    private val savedStateHandle: SavedStateHandle = SavedStateHandle(),
    private val registryRepository: List<FoodRegistry> = emptyList(),
): ViewModel() {
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

    private val _foodRegistryId = savedStateHandle
        .getStateFlow("foodRegistryId", "")
    val foodRegistryId: StateFlow<String> = _foodRegistryId

    val foodRegistry: StateFlow<FoodRegistry?> = _foodRegistryId.map {
        registryRepository.find { it.id == _foodRegistryId.value }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )

    fun setFoodRegistryId(id: String) {
        savedStateHandle["foodRegistryId"] = id
    }
}