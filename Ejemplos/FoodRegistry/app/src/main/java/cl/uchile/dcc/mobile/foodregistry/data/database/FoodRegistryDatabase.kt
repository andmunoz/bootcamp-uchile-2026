package cl.uchile.dcc.mobile.foodregistry.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FoodRegistry::class, FoodType::class],
    version = 1,
    exportSchema = true
)
abstract class FoodRegistryDatabase : RoomDatabase() {
    abstract fun foodRegistryDAO(): FoodRegistryDAO

    companion object {
        @Volatile
        private var INSTANCE: FoodRegistryDatabase? = null

        fun getInstance(context: Context): FoodRegistryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodRegistryDatabase::class.java,
                    "food_registry_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}