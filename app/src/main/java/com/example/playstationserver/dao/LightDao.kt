package com.example.playstationserver.dao

import androidx.room.Dao
import com.example.playstationserver.model.data.light.LightData

@Dao()
interface LightDao {
    @androidx.room.Query("SELECT * FROM light")
    suspend fun getAllLight(): List<LightData>
}