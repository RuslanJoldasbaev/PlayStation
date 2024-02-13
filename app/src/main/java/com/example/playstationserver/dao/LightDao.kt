package com.example.playstationserver.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.playstationserver.model.data.light.LightData

@Dao()
interface LightDao {
    @Query("SELECT * FROM light")
    suspend fun getAllLight(): List<LightData>
}