package com.juanchaverra.citofapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Apto::class], version = 1)
abstract class AptoDataBase: RoomDatabase() {

    abstract fun AptoDAO(): AptoDAO
}

