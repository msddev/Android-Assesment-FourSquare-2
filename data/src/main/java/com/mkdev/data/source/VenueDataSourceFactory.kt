package com.mkdev.data.source

import com.mkdev.data.repository.VenueDataSource
import javax.inject.Inject

class VenueDataSourceFactory @Inject constructor(
    private val cacheDataSource: VenueCacheDataSource,
    private val remoteDataSource: VenueRemoteDataSource
) {
    fun getDataStore(isCached: Boolean): VenueDataSource {
        return if (isCached) {
            getCacheDataSource()
        } else {
            getRemoteDataSource()
        }
    }

    fun getRemoteDataSource(): VenueDataSource {
        return remoteDataSource
    }

    fun getCacheDataSource(): VenueDataSource {
        return cacheDataSource
    }
}