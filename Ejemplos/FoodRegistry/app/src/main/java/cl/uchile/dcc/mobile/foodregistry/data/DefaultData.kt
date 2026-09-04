package cl.uchile.dcc.mobile.foodregistry.data

import cl.uchile.dcc.mobile.foodregistry.data.database.FoodType

object DefaultData {
    val foodTypes = listOf(
        FoodType(name = "Desayuno"),
        FoodType(name = "Almuerzo"),
        FoodType(name = "Once"),
        FoodType(name = "Cena"),
    )
}