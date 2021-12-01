package com.mkdev.foursquarecodechallenge.di

import com.mkdev.foursquarecodechallenge.BuildConfig
import com.mkdev.remote.api.FourSquareService
import com.mkdev.remote.api.ServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideFourSquareService(): FourSquareService =
        ServiceFactory.create(BuildConfig.DEBUG)
}