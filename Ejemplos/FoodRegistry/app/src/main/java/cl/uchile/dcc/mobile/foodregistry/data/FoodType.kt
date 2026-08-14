package cl.uchile.dcc.mobile.foodregistry.data

import java.util.UUID

data class FoodType(
    val id: String = UUID.randomUUID().toString(),
    val name: String
)
