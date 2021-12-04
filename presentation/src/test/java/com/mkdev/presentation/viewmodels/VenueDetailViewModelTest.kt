package com.mkdev.presentation.viewmodels

import androidx.lifecycle.Observer
import com.mkdev.domain.intractor.GetVenueDetailUseCase
import com.mkdev.domain.model.VenueDetailUIModel
import com.mkdev.presentation.fakes.FakePresentationData
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
class VenueDetailViewModelTest : PresentationBaseTest() {

    private lateinit var venueDetailViewModel: VenueDetailViewModel

    @Mock
    lateinit var getVenueDetailUseCase: GetVenueDetailUseCase

    @Mock
    private lateinit var observer: Observer<VenueDetailUIModel>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        venueDetailViewModel = VenueDetailViewModel(
            dispatcher,
            getVenueDetailUseCase
        )
        venueDetailViewModel.venuesDetail.observeForever(observer)
    }

    @Test
    fun `get venue detail with venue-id should return venue complete detail from use-case`() =
        dispatcher.test.runBlockingTest {
            // Given
            val venueId = "1"
            val venue = FakePresentationData.getVenues(1)[0]
            whenever(getVenueDetailUseCase(venueId)).thenReturn(flowOf(venue))

            // When
            venueDetailViewModel.getVenueDetail(venueId)

            // Then
            verify(observer).onChanged(VenueDetailUIModel.Loading)
            verify(observer).onChanged(VenueDetailUIModel.Success(venue))
        }

    @Test
    fun `get venue detail with venue-id should return error from use-case`() =
        dispatcher.test.runBlockingTest {
            // Given
            val venueId = "5710f798498e1bdf342c9ed8"
            val errorMessage = "Internal server error"
            whenever(getVenueDetailUseCase(venueId)).thenAnswer {
                throw IOException(errorMessage)
            }

            // When
            venueDetailViewModel.getVenueDetail(venueId)

            // Then
            verify(observer).onChanged(VenueDetailUIModel.Loading)
            verify(observer).onChanged(VenueDetailUIModel.Error(errorMessage))
        }

    @After
    fun tearDown() {
        venueDetailViewModel.onCleared()
    }
}