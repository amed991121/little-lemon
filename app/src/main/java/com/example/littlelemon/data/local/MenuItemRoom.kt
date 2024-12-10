package com.example.littlelemon.data.local

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "menu_items")
data class MenuItemRoom(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "category")
    val category: String
)

@Dao
interface MenuItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(menuItems: List<MenuItemRoom>)

    @Query("SELECT * FROM menu_items")
    suspend fun getAll(): List<MenuItemRoom>
}
