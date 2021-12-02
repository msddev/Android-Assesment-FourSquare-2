package com.mkdev.presentation.viewmodels

import android.location.Location
import androidx.lifecycle.LiveData
import com.mkdev.domain.intractor.GetVenueDetailUseCase
import com.mkdev.domain.model.VenueDetailUIModel
import com.mkdev.presentation.utils.CoroutineContextProvider
import com.mkdev.presentation.utils.UiAwareLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class VenueDetailViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val getVenueDetailUseCase: GetVenueDetailUseCase
) : BaseViewModel(contextProvider) {

    private var location = Location("location")

    override val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            _venuesDetail.postValue(
                VenueDetailUIModel.Error(
                    exception.message ?: "Occurred ViewModel Exception!"
                )
            )
        }

    private val _venuesDetail = UiAwareLiveData<VenueDetailUIModel>()
    val venuesDetail: LiveData<VenueDetailUIModel> = _venuesDetail
    fun getVenueDetail(id: String) {
        _venuesDetail.postValue(VenueDetailUIModel.Loading)
        launchCoroutineIO {
            loadVenueDetail(id)
        }
    }

    private suspend fun loadVenueDetail(id: String) {
        getVenueDetailUseCase(id).collect {
            location.latitude = it.latitude
            location.longitude = it.longitude
            _venuesDetail.postValue(VenueDetailUIModel.Success(it))
        }
    }

    fun getLocationLatLng(): Location = location
}