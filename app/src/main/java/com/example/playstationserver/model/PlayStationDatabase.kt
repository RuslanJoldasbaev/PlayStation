package com.example.playstationserver.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.playstationserver.dao.LightDao
import com.example.playstationserver.dao.NightDao
import com.example.playstationserver.model.data.light.LightData
import com.example.playstationserver.model.data.night.NightData

@Database(entities = [LightData::class, NightData::class], version = 1)
abstract class PlayStationDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: PlayStationDatabase? = null

        fun getInstance(context: Context): PlayStationDatabase {
            INSTANCE?.let {
                return it
            }

            val db = Room.databaseBuilder(
                context, PlayStationDatabase::class.java, "play_station.db"
            ).createFromAsset("play_station.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

            INSTANCE = db
            return db
        }
    }

    abstract fun getLightDao(): LightDao
    abstract fun getNightDao(): NightDao
}