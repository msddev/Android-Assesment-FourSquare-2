package com.mkdev.remote.api

import com.mkdev.remote.models.BaseApiResponse
import com.mkdev.remote.models.ExploreVenuesResponse
import com.mkdev.remote.models.VenueDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FourSquareService {

    /**
     * sortByDistance : 0 is unSorted and 1 Sorted result
     */
    @GET("v2/venues/explore")
    suspend fun getNearVenues(
        @Query("ll") latLng: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("sortByDistance") sortByDistance: Int = 1
    ): BaseApiResponse<ExploreVenuesResponse>

    @GET("v2/venues/{id}")
    suspend fun getVenueById(
        @Path("id") id: String
    ): BaseApiResponse<VenueDetailResponse>
}
