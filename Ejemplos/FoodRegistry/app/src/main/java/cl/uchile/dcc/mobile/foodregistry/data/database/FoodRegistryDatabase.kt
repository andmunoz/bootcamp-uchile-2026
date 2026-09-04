package cl.uchile.dcc.mobile.foodregistry.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FoodRegistry::class, FoodType::class],
    version = 2,
    exportSchema = false
)
abstract class FoodRegistryDatabase : RoomDatabase() {
    abstract val foodRegistryDAO: FoodRegistryDAO

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

        /*
            val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override suspend fun migrate(connection: SQLiteConnection) {
                connection.execSQL("ALTER TABLE FoodRegistry ADD COLUMN tipo_id TEXT")
            }
         */
    }
}