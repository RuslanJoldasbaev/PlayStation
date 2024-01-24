package com.example.playstationserver.model.data.night

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "night")
data class NightData(
    @PrimaryKey val id: Int,
    val name: String,
    val img: String,
)
