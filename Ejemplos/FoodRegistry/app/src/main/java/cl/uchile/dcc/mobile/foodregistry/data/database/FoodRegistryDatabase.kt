package cl.uchile.dcc.mobile.foodregistry.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import cl.uchile.dcc.mobile.foodregistry.data.DefaultData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Database(
    entities = [FoodRegistry::class, FoodType::class],
    version = 1, // Al final borré la base de datos físicamente del dispositivo
    exportSchema = true
)
abstract class FoodRegistryDatabase : RoomDatabase() {
    abstract fun foodRegistryDAO(): FoodRegistryDAO
    abstract fun foodTypeDAO(): FoodTypeDAO

    companion object {
        @Volatile
        private var INSTANCE: FoodRegistryDatabase? = null

        val MIGRATION = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Aquí va el código para la migración de la Base de Datos
            }
        }

        fun getInstance(context: Context): FoodRegistryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodRegistryDatabase::class.java,
                    "food_registry_database"
                ).addCallback(object: Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            populateData()
                        }
                    }
                }
                ).addMigrations(MIGRATION).build()
                INSTANCE = instance
                instance
            }
        }

        suspend fun populateData() {
            val dao = INSTANCE?.foodTypeDAO() ?: return
            val foodTypes = DefaultData.foodTypes
            if (dao.getAllFoodTypes().first().isEmpty()) {
                foodTypes.forEach {
                    dao.addFoodType(it)
                }
            }
        }
    }
}