package com.mkdev.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mkdev.domain.intractor.GetNearVenuesUseCase
import com.mkdev.domain.model.VenueParams
import com.mkdev.domain.model.VenueUIModel
import com.mkdev.presentation.locationProviders.LocationProvider
import com.mkdev.presentation.utils.CoroutineContextProvider
import com.mkdev.presentation.utils.UiAwareLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class VenuesListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val getNearVenuesUseCase: GetNearVenuesUseCase,
    private val locationProvider: LocationProvider
) : BaseViewModel(contextProvider) {

    override val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            _venuesList.postValue(
                VenueUIModel.Error(
                    exception.message ?: "Occurred ViewModel Exception!"
                )
            )
        }

    private val _venuesList = UiAwareLiveData<VenueUIModel>()
    val venuesList: LiveData<VenueUIModel> = _venuesList
    fun getVenues(params: VenueParams) {
        _venuesList.postValue(VenueUIModel.Loading)
        launchCoroutineIO {
            loadVenues(params)
        }
    }

    private suspend fun loadVenues(params: VenueParams) {
        getNearVenuesUseCase(params).collect {
            _venuesList.postValue(VenueUIModel.Success(it))
        }
    }

    private val _currentLocation = MutableLiveData<String>()
    val currentLocation: LiveData<String> = _currentLocation
    fun getCurrentLocationLatLng() {
        launchCoroutineIO {
            loadCurrentLocationLatLng()
        }
    }

    private suspend fun loadCurrentLocationLatLng() =
        _currentLocation.postValue(locationProvider.getLocationLatLng())

    private val _isLocationChanged = MutableLiveData<Boolean>()
    val isLocationChanged: LiveData<Boolean> = _isLocationChanged
    fun getCheckLocationChanged(lat: Double, lng: Double) {
        launchCoroutineIO {
            loadCheckLocationChanged(lat, lng)
        }
    }

    private suspend fun loadCheckLocationChanged(lat: Double, lng: Double) {
        _isLocationChanged.postValue(
            locationProvider.isLocationChanged(lat, lng)
        )
    }
}