package com.mkdev.domain.model

sealed class VenueDetailUIModel : UiAwareModel() {
    object Loading : VenueDetailUIModel()
    data class Success(val data: Venue) : VenueDetailUIModel()
    data class Error(var error: String) : VenueDetailUIModel()
}
