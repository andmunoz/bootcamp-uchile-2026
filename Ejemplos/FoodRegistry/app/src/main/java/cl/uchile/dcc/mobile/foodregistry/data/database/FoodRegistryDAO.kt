package cl.uchile.dcc.mobile.foodregistry.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodRegistryDAO {
    // CREATE
    @Insert
    suspend fun addFoodRegistry(foodRegistry: FoodRegistry): Long

    // READ
    @Query("SELECT * FROM food_registry ORDER BY fecha DESC")
    suspend fun getAllFoodRegistry(): List<FoodRegistry>

    @Query("SELECT * FROM food_registry WHERE id = :id")
    suspend fun getFoodRegistryById(id: String): List<FoodRegistry>

    @Query("SELECT * FROM food_registry WHERE fecha = :fecha")
    suspend fun getFoodRegistryByDate(fecha: String): List<FoodRegistry>

    @Query("SELECT * FROM food_registry WHERE tipo_id = :tipoId")
    suspend fun getFoodRegistryByType(tipoId: String): List<FoodRegistry>

    // UPDATE
    @Update
    suspend fun updateFoodRegistry(foodRegistry: FoodRegistry)

    // DELETE
    @Delete
    suspend fun deleteFoodRegistry(foodRegistry: FoodRegistry)
}