package com.mkdev.presentation.viewmodels

import androidx.lifecycle.LiveData
import com.mkdev.domain.intractor.GetNearVenuesUseCase
import com.mkdev.domain.model.VenueParams
import com.mkdev.domain.model.VenueUIModel
import com.mkdev.presentation.utils.CoroutineContextProvider
import com.mkdev.presentation.utils.UiAwareLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class VenuesListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val getNearVenuesUseCase: GetNearVenuesUseCase
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
}