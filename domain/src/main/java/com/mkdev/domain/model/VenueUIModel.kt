package com.mkdev.domain.model

sealed class VenueUIModel : UiAwareModel() {
    object Loading : VenueUIModel()
    data class Success(val data: List<Venue>) : VenueUIModel()
    data class Error(var error: String) : VenueUIModel()
}
