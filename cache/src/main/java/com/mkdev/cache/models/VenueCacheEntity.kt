package com.mkdev.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mkdev.cache.utils.CacheConstants.VENUE_TABLE_NAME

@Entity(tableName = VENUE_TABLE_NAME)
data class VenueCacheEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String? = "",
    val distance: Int,
    @ColumnInfo(name = "category_type")
    val categoryType: String,
    @ColumnInfo(name = "category_icon")
    val categoryIcon: String,
    val picture: String,
    val phone: String,
    val likes: Int,
    @ColumnInfo(name = "user_location")
    val userLocation: String
)