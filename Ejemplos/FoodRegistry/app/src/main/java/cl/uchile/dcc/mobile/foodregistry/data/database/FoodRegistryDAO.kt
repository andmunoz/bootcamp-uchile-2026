package cl.uchile.dcc.mobile.foodregistry.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodRegistryDAO {
    // CREATE
    @Insert
    suspend fun addFoodRegistry(foodRegistry: FoodRegistry): Long

    // READ
    @Query("SELECT * FROM food_registry ORDER BY fecha DESC")
    fun getAllFoodRegistry(): Flow<List<FoodRegistry>>

    @Query("SELECT * FROM food_registry WHERE id = :id")
    fun getFoodRegistryById(id: String): Flow<List<FoodRegistry>>

    @Query("SELECT * FROM food_registry WHERE fecha = :fecha")
    fun getFoodRegistryByDate(fecha: String): Flow<List<FoodRegistry>>

    @Query("SELECT * FROM food_registry WHERE tipo_id = :tipoId")
    fun getFoodRegistryByType(tipoId: String): Flow<List<FoodRegistry>>

    // UPDATE
    @Update
    suspend fun updateFoodRegistry(foodRegistry: FoodRegistry)

    // DELETE
    @Delete
    suspend fun deleteFoodRegistry(foodRegistry: FoodRegistry)
}