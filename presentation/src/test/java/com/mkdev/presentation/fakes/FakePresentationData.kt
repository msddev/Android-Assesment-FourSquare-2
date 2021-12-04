package com.mkdev.presentation.fakes

import com.mkdev.domain.model.Venue
import com.mkdev.domain.model.VenueParams
import com.mkdev.presentation.fakes.FakeValueFactory.randomDouble
import com.mkdev.presentation.fakes.FakeValueFactory.randomInt
import com.mkdev.presentation.fakes.FakeValueFactory.randomString

object FakePresentationData {

    fun getVenues(size: Int): List<Venue> {
        val venues = mutableListOf<Venue>()
        repeat(size) {
            venues.add(createVenue())
        }

        return venues
    }

    fun getVenueParams(): VenueParams = createVenueParams()

    private fun createVenue(): Venue {
        return Venue(
            id = randomString(),
            name = randomString(),
            latitude = randomDouble(),
            longitude = randomDouble(),
            address = randomString(),
            distance = randomInt(),
            categoryType = randomString(),
            categoryIcon = randomString(),
            picture = randomString(),
            phone = randomString(),
            likes = randomInt(),
            userCurrentLatLng = randomString()
        )
    }

    private fun createVenueParams(): VenueParams {
        return VenueParams(
            latLng = randomString(),
            limit = randomInt(),
            offset = randomInt()
        )
    }
}