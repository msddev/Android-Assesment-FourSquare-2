package com.mkdev.domain.model

sealed class VenueDetailUIModel : UiAwareModel() {
    object Loading : VenueDetailUIModel()
    data class Success(val data: VenueDetail) : VenueDetailUIModel()
    data class Error(var error: String) : VenueDetailUIModel()
}
