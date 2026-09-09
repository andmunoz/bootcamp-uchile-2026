package cl.uchile.dcc.mobile.foodregistry.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodTypeDAO {
    @Insert
    suspend fun addFoodType(foodType: FoodType): Long

    @Query("SELECT * FROM food_type ORDER BY name")
    fun getAllFoodTypes(): Flow<List<FoodType>>

    @Update
    suspend fun updateFoodType(foodType: FoodType)

    @Delete
    suspend fun deleteFoodType(foodType: FoodType)
}