package com.example.littlelemon.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuItemRoom::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getMenuItemDao(): MenuItemDao
}