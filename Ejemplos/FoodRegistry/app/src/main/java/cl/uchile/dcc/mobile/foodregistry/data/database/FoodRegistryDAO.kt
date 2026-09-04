package cl.uchile.dcc.mobile.foodregistry.data.database

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update

@Dao
interface FoodRegistryDAO {
    // CREATE
    @Insert
    suspend fun addFoodRegistry(foodRegistry: FoodRegistry): Long

    // READ
    @Query("SELECT * FROM FoodRegistry")
    suspend fun getAllFoodRegistry(): List<FoodRegistry>

    @Query("SELECT * FROM FoodRegistry WHERE id = :id")
    suspend fun getOneFoodRegistry(id: String): List<FoodRegistry>

    // UPDATE
    @Update
    suspend fun updateFoodRegistry(foodRegistry: FoodRegistry)

    // DELETE
    @Delete
    suspend fun deleteFoodRegistry(foodRegistry: FoodRegistry)
}