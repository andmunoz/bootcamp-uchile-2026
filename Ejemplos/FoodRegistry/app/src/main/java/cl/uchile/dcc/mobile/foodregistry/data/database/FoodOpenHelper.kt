package cl.uchile.dcc.mobile.foodregistry.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class FoodOpenHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    companion object {
        const val DATABASE_NAME = "foodregistry.db"
        const val DATABASE_VERSION = 2
        const val TABLE_FOOD_REGISTRY = "FoodRegistry"
        const val TABLE_FOOD_TYPE = "FoodType"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val tableFoodRegistry = """
            CREATE TABLE $TABLE_FOOD_REGISTRY (
                _id TEXT PRIMARY KEY,
                fecha TEXT,
                tipo_id TEXT,
                descripcion TEXT,
                calorias INTEGER,
                carbohidratos INTEGER
            )
        """.trimIndent()
        db?.execSQL(tableFoodRegistry)
        val tableFoodType = """
            CREATE TABLE $TABLE_FOOD_TYPE (
                _id TEXT PRIMARY KEY,
                nombre TEXT
            )
        """.trimIndent()
        db?.execSQL(tableFoodType)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        if (newVersion > oldVersion) {
            Log.d("FoodOpenHelper", "Actualizando Base de Datos de v$oldVersion a v$newVersion")
            // Aquí va el cambio de versión (ALTER TABLE)
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_FOOD_REGISTRY")
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_FOOD_TYPE")
            onCreate(db)
        }
    }
}