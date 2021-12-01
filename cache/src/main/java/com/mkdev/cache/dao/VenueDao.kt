package com.mkdev.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mkdev.cache.models.VenueCacheEntity
import com.mkdev.cache.utils.CacheConstants.VENUE_TABLE_NAME

@Dao
interface VenueDao {

    @Query("select * from $VENUE_TABLE_NAME")
    fun getVenues(): List<VenueCacheEntity>

    @Query("select * from $VENUE_TABLE_NAME where user_location= :latLng")
    fun getVenues(latLng: String): List<VenueCacheEntity>

    @Query("select * from $VENUE_TABLE_NAME where id= :id")
    fun getVenueById(id: String): VenueCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVenue(venues: VenueCacheEntity)

    @Query("delete from $VENUE_TABLE_NAME")
    fun deleteAllVenues()

    @Query("select user_location from $VENUE_TABLE_NAME")
    fun getLatestLocations(): List<String>
}
