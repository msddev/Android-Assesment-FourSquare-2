package com.mkdev.presentation.viewmodels

import androidx.lifecycle.Observer
import com.mkdev.domain.intractor.GetNearVenuesUseCase
import com.mkdev.domain.model.VenueUIModel
import com.mkdev.presentation.fakes.FakePresentationData
import com.mkdev.presentation.locationProviders.LocationProvider
import com.mkdev.presentation.utils.PresentationBaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException

@ExperimentalCoroutinesApi
class VenuesListViewModelTest : PresentationBaseTest() {

    private lateinit var venuesListViewModel: VenuesListViewModel

    @Mock
    private lateinit var getNearVenuesUseCase: GetNearVenuesUseCase

    @Mock
    private lateinit var locationProvider: LocationProvider

    @Mock
    private lateinit var observer: Observer<VenueUIModel>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        venuesListViewModel = VenuesListViewModel(
            dispatcher,
            getNearVenuesUseCase,
            locationProvider
        )

        venuesListViewModel.venuesList.observeForever(observer)
    }

    @Test
    fun `get venues should return venues list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val params = FakePresentationData.getVenueParams()
            val venues = FakePresentationData.getVenues(10)
            whenever(getNearVenuesUseCase(params)).thenReturn(flowOf(venues))

            // Act (When)
            venuesListViewModel.getVenues(params)

            // Assert (Then)
            verify(observer).onChanged(VenueUIModel.Loading)
            verify(observer).onChanged(VenueUIModel.Success(venues))
        }

    @Test
    fun `get venues should return empty venues list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val params = FakePresentationData.getVenueParams()
            val venues = FakePresentationData.getVenues(0)
            whenever(getNearVenuesUseCase(params)).thenReturn(flowOf(venues))

            // Act (When)
            venuesListViewModel.getVenues(params)

            // Assert (Then)
            verify(observer).onChanged(VenueUIModel.Loading)
            verify(observer).onChanged(VenueUIModel.Success(venues))
        }

    @Test
    fun `get venues should return error from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val params = FakePresentationData.getVenueParams()
            val errorMessage = "Internal server error"
            whenever(getNearVenuesUseCase(params)).thenAnswer {
                throw IOException(errorMessage)
            }

            // Act (When)
            venuesListViewModel.getVenues(params)

            // Assert (Then)
            verify(observer).onChanged(VenueUIModel.Loading)
            verify(observer).onChanged(VenueUIModel.Error(errorMessage))
        }

    @After
    fun tearDown() {
        venuesListViewModel.onCleared()
    }
}

