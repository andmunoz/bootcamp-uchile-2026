package cl.uchile.dcc.mobile.foodregistry.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "food_registry")
data class FoodRegistry(
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "fecha")
    val fecha: String, // Formato "dd/MM/yyyy"
    @ColumnInfo(name = "tipo_id")
    val tipoId: String,
    @ColumnInfo(name = "descripcion")
    val descripcion: String?,
    @ColumnInfo(name = "calorias")
    val calorias: Int,
    @ColumnInfo(name = "carbohidratos")
    val carbohidratos: Int
)
