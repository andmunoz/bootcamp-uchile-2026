package cl.uchile.dcc.mobile.apiexample.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "remoteId")
    val remoteId: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "active")
    val active: Boolean = false,
    @ColumnInfo(name = "created")
    val created: String = "",
    @ColumnInfo(name = "updated")
    val updated: String = "",
)