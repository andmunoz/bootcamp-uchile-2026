package cl.uchile.dcc.mobile.foodregistry.data.repository

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import cl.uchile.dcc.mobile.foodregistry.data.database.FoodRegistry
import cl.uchile.dcc.mobile.foodregistry.data.database.FoodRegistryDatabase
import cl.uchile.dcc.mobile.foodregistry.data.database.FoodType

class FoodDataRepository(
    private val context: Context
) {
    private val database = FoodRegistryDatabase.getInstance(context)
    private val foodRegistryDAO = database.foodRegistryDAO()
    private val foodTypeDAO = database.foodTypeDAO()

    // CREATE para FoodRegistry
    suspend fun addFoodRegistry(foodRegistry: FoodRegistry): Long {
        Log.d("FoodDataRepository", "Adding food registry: $foodRegistry")
        return foodRegistryDAO.addFoodRegistry(foodRegistry)
    }

    // READ para FoodRegistry
    fun getAllFoodRegistry(): Flow<List<FoodRegistry>> {
        Log.d("FoodDataRepository", "Listing all food registries")
        return foodRegistryDAO.getAllFoodRegistry()
    }

    // READ para FoodType
    fun getFoodType(): Flow<List<FoodType>> {
        Log.d("FoodDataRepository", "Listing all food types")
        return foodTypeDAO.getAllFoodTypes()
    }
}