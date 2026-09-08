package cl.uchile.dcc.mobile.foodregistry.data.repository

import android.content.Context
import android.util.Log
import cl.uchile.dcc.mobile.foodregistry.data.database.FoodRegistry
import cl.uchile.dcc.mobile.foodregistry.data.database.FoodRegistryDatabase

class FoodDataRepository(
    private val context: Context
) {
    private val database = FoodRegistryDatabase.getInstance(context)
    private val dao = database.foodRegistryDAO()

    // CREATE del CRUD
    suspend fun addFoodRegistry(foodRegistry: FoodRegistry): Long {
        Log.d("FoodDataRepository", "Adding food registry: $foodRegistry")
        return dao.addFoodRegistry(foodRegistry)
    }

    // READ del CRUD
    suspend fun getAllFoodRegistry(): List<FoodRegistry> {
        Log.d("FoodDataRepository", "Listing all food registries")
        return dao.getAllFoodRegistry()
    }
}