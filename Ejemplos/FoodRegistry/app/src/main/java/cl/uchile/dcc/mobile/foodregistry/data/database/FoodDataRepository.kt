package cl.uchile.dcc.mobile.foodregistry.data.database

import android.content.ContentValues
import android.content.Context
import cl.uchile.dcc.mobile.foodregistry.data.FoodRegistry

class FoodDataRepository(
    private val context: Context
) {
    private val dbHelper = FoodOpenHelper(context)

    // CREATE del CRUD
    fun addFoodRegistry(foodRegistry: FoodRegistry) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("_id", foodRegistry.id)
            put("fecha", foodRegistry.fecha)
            put("tipo_id", foodRegistry.tipoId)
            put("descripcion", foodRegistry.descripcion)
            put("calorias", foodRegistry.calorias)
            put("carbohidratos", foodRegistry.carbohidratos)
        }
        db.insert("FoodRegistry", null, values)
        db.close()
    }

    // READ del CRUD
    fun getAllFoodRegistry(): List<FoodRegistry> {
        val foodRegistryList = mutableListOf<FoodRegistry>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            FoodOpenHelper.TABLE_FOOD_REGISTRY,
            null,
            null,
            null,
            null,
            null,
            "fecha DESC"
        )
        with(cursor) {
            while (moveToNext()) {
                val id = getString(getColumnIndexOrThrow("_id"))
                val fecha = getString(getColumnIndexOrThrow("fecha"))
                val tipoId = getString(getColumnIndexOrThrow("tipo_id"))
                val descripcion = getString(getColumnIndexOrThrow("descripcion"))
                val calorias = getInt(getColumnIndexOrThrow("calorias"))
                val carbohidratos = getInt(getColumnIndexOrThrow("carbohidratos"))
                foodRegistryList.add(
                    FoodRegistry(
                        id = id,
                        fecha = fecha,
                        tipoId = tipoId,
                        descripcion = descripcion,
                        calorias = calorias,
                        carbohidratos = carbohidratos
                    )
                )
            }
        }
        cursor.close()
        db.close()
        return foodRegistryList
    }

    // UPDATE del CRUD
    fun updateFoodRegistry(foodRegistry: FoodRegistry) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("fecha", foodRegistry.fecha)
            put("tipo_id", foodRegistry.tipoId)
            put("descripcion", foodRegistry.descripcion)
            put("calorias", foodRegistry.calorias)
            put("carbohidratos", foodRegistry.carbohidratos)
        }
        db.update(
            FoodOpenHelper.TABLE_FOOD_REGISTRY,
            values,
            "_id = ?",
            arrayOf(foodRegistry.id)
        )
        db.close()
    }

    // DELETE del CRUD
    fun deleteFoodRegistry(foodRegistry: FoodRegistry) {
        val db = dbHelper.writableDatabase
        db.delete(
            FoodOpenHelper.TABLE_FOOD_REGISTRY,
            "_id = ?",
            arrayOf(foodRegistry.id)
        )
        db.close()
    }
}