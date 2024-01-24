package com.example.playstationserver.dao

import androidx.room.Dao
import com.example.playstationserver.model.data.night.NightData

@Dao()
interface NightDao {
    @androidx.room.Query("SELECT * FROM night")
    suspend fun getAllNight(): List<NightData>
}