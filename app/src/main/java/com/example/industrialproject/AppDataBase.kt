package com.example.industrialproject

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], exportSchema = false, version = 1)
abstract class AppDataBase :RoomDatabase(){
    abstract fun getDao():Dao
}