package com.mkdev.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mkdev.cache.dao.VenueDao
import com.mkdev.cache.models.VenueCacheEntity
import com.mkdev.cache.utils.CacheConstants
import com.mkdev.cache.utils.Migrations
import javax.inject.Inject

@Database(
    entities = [VenueCacheEntity::class],
    version = Migrations.DB_VERSION,
    exportSchema = false
)
abstract class FourSquareDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedVenueDao(): VenueDao

    companion object {
        @Volatile
        private var INSTANCE: FourSquareDatabase? = null

        fun getInstance(context: Context): FourSquareDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FourSquareDatabase::class.java,
            CacheConstants.DB_NAME
        ).build()
    }
}
