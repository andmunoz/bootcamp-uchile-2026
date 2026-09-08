package cl.uchile.dcc.mobile.foodregistry.ui.screenstates

import cl.uchile.dcc.mobile.foodregistry.data.database.FoodRegistry

sealed class FoodRegistryEventState {
    data class ShowSnackbar(val message: String) : FoodRegistryEventState()
}