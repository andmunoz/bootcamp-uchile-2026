package cl.uchile.dcc.mobile.foodregistry.ui.screenstates

import cl.uchile.dcc.mobile.foodregistry.data.FoodRegistry

sealed class FoodRegistryEventState {
    object Loading: FoodRegistryEventState()
    object Empty: FoodRegistryEventState()
    data class Success(val foodRegistry: List<FoodRegistry>, val foodFilter: String?): FoodRegistryEventState()
    data class Error(val message: String): FoodRegistryEventState()
}