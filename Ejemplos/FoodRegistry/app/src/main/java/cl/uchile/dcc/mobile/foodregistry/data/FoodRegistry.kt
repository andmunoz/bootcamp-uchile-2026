package cl.uchile.dcc.mobile.foodregistry.data

import java.util.UUID

data class FoodRegistry(
    val id: String = UUID.randomUUID().toString(),
    val fecha: String, // Formato "dd/MM/yyyy"
    val tipoId: String,
    val descripcion: String,
    val calorias: Int,
    val carbohidratos: Int
)
