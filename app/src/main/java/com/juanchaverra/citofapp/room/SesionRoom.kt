package com.juanchaverra.citofapp.room

import android.app.Application
import androidx.room.Room

class SesionRoom: Application() {
    companion object {
        lateinit var database: AptoDataBase
    }

    override fun onCreate() {
        super.onCreate()

        SesionRoom.database = Room.databaseBuilder(
            this,
            AptoDataBase::class.java,
            "aptos_DB"
        ).allowMainThreadQueries()
            .build()
    }
}