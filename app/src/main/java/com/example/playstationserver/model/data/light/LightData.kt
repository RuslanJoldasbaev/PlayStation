package com.example.playstationserver.model.data.light

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "light")
data class LightData(
    @PrimaryKey val id: Int,
    val name: String,
    val img: String,
    var is_fav: Int
)
