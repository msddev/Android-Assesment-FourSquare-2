package com.mkdev.foursquarecodechallenge.di

import android.content.Context
import com.mkdev.cache.dao.VenueDao
import com.mkdev.cache.database.FourSquareDatabase
import com.mkdev.cache.datasourceImpl.VenueCacheImpl
import com.mkdev.cache.utils.CachePreferencesHelper
import com.mkdev.data.repository.VenueCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): FourSquareDatabase {
        return FourSquareDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideVenueDao(database: FourSquareDatabase): VenueDao {
        return database.cachedVenueDao()
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(@ApplicationContext context: Context): CachePreferencesHelper {
        return CachePreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun provideVenueCache(cacheImpl: VenueCacheImpl): VenueCache = cacheImpl
}
