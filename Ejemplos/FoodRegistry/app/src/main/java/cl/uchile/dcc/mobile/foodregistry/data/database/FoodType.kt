package cl.uchile.dcc.mobile.foodregistry.data.database

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import java.util.UUID

@Entity(tableName = "food_type")
data class FoodType(
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "name")
    val name: String
)
