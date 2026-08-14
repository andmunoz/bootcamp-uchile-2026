package cl.uchile.dcc.mobile.foodregistry.ui.screenstates

import cl.uchile.dcc.mobile.foodregistry.data.DefaultData
import cl.uchile.dcc.mobile.foodregistry.data.FoodRegistry
import cl.uchile.dcc.mobile.foodregistry.data.FoodType

data class FoodRegistryFormState(
    // Registro de Comidas
    val fecha: String = "",
    val foodTypes: List<FoodType> = DefaultData.foodTypes,
    val tipoId: String = "",
    val descripcion: String = "",
    val calorias: String? = null,
    val carbohidratos: String? = null,
    // Lista de Comidas
    val foodRegistry: List<FoodRegistry> = emptyList(),
    val foodFilter: String = "",
    // Configuración
    val nombre: String = "",
)
