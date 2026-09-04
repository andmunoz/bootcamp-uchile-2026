package cl.uchile.dcc.mobile.foodregistry.ui.screenstates

import cl.uchile.dcc.mobile.foodregistry.data.DefaultData
import cl.uchile.dcc.mobile.foodregistry.data.database.FoodType

data class FoodRegistryFormState(
    // Datos del formulario
    val fecha: String = "",
    val tipoId: String = "",
    val descripcion: String? = null,
    val calorias: String? = null,
    val carbohidratos: String? = null,

    // Dropdown de comidas
    val foodTypes: List<FoodType> = DefaultData.foodTypes,
    val showDatePicker: Boolean = false
)