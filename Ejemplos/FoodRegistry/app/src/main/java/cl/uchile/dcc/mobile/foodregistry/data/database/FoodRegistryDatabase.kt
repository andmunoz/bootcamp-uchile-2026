package cl.uchile.dcc.mobile.foodregistry.data.database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.room3.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL

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