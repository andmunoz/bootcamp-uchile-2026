package cl.uchile.dcc.mobile.foodregistry.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_registry")
data class FoodRegistry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "fecha")
    val fecha: String, // Formato "dd/MM/yyyy"
    @ColumnInfo(name = "tipo_id")
    val tipoId: Int,
    @ColumnInfo(name = "descripcion")
    val descripcion: String?,
    @ColumnInfo(name = "calorias")
    val calorias: Int,
    @ColumnInfo(name = "carbohidratos")
    val carbohidratos: Int
)
