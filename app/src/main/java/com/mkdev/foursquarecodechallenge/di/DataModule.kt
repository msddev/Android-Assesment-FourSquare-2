package com.mkdev.foursquarecodechallenge.di

import com.mkdev.data.VenueRepositoryImpl
import com.mkdev.domain.repository.VenueRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideVenueRepository(
        venueRepositoryImpl: VenueRepositoryImpl
    ): VenueRepository = venueRepositoryImpl
}