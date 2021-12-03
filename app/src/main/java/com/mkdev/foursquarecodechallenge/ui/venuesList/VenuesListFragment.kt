package com.mkdev.foursquarecodechallenge.ui.venuesList

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mkdev.domain.model.Venue
import com.mkdev.domain.model.VenueParams
import com.mkdev.domain.model.VenueUIModel
import com.mkdev.foursquarecodechallenge.base.BaseFragment
import com.mkdev.foursquarecodechallenge.databinding.FragmentVenuesListBinding
import com.mkdev.foursquarecodechallenge.extension.observe
import com.mkdev.foursquarecodechallenge.ui.MainActivity.Companion.LOCATION_CHECKER_BROADCAST
import com.mkdev.presentation.viewmodels.VenuesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VenuesListFragment : BaseFragment<FragmentVenuesListBinding, VenuesListViewModel>() {

    private val limit = 20
    private var offset = 0
    private var isEndOfList: Boolean = false
    private lateinit var locationBroadcast: BroadcastReceiver
    private var currentLatLng: String = ""

    @Inject
    lateinit var venuesAdapter: VenueAdapter

    override val viewModel: VenuesListViewModel by viewModels()

    override fun getViewBinding(): FragmentVenuesListBinding =
        FragmentVenuesListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCurrentLocationLatLng()
        observe(viewModel.currentLocation, ::onCurrentLocation)
        observe(viewModel.isLocationChanged, ::onIsLocationChanged)
        observe(viewModel.venuesList, ::onViewStateChange)

        setupRecyclerView()
        setupLocationBroadcast()
    }

    private fun onCurrentLocation(latLng: String) {
        currentLatLng = latLng
        Log.d("location", "Current Location : $currentLatLng")

        viewModel.getVenues(
            VenueParams(
                latLng = currentLatLng,
                limit = limit,
                offset = offset
            )
        )
    }

    private fun onIsLocationChanged(isChanged: Boolean) {
        Log.d("location", "Location Changed: $isChanged")
        if (isChanged) {
            viewModel.getCurrentLocationLatLng()
        }
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewVenue.apply {
            adapter = venuesAdapter
            layoutManager = linearLayoutManager
        }

        venuesAdapter.setItemClickListener { venue ->
            findNavController().navigate(
                VenuesListFragmentDirections.actionVenuesListFragmentToVenueDetailFragment(
                    venueId = venue.id,
                    venueDistance = venue.distance.toString()
                )
            )
        }

        binding.recyclerViewVenue.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy <= 0) return

                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val pastItemCount = linearLayoutManager.findFirstVisibleItemPosition()

                if (viewModel.venuesList.value !is VenueUIModel.Loading && !isEndOfList) {
                    if ((visibleItemCount + pastItemCount) >= totalItemCount) {
                        viewModel.getVenues(
                            VenueParams(
                                latLng = "35.730673, 51.458880",
                                limit = limit,
                                offset = offset
                            )
                        )
                    }
                }
            }
        })
    }

    private fun onViewStateChange(event: VenueUIModel) {
        if (event.isRedelivered) return
        when (event) {
            is VenueUIModel.Loading -> {
                handleLoading(true)
            }
            is VenueUIModel.Success -> {
                handleLoading(false)
                handleVenusResponse(event.data)
            }
            is VenueUIModel.Error -> {
                handleErrorMessage(event.error)
            }
        }
    }

    private fun handleVenusResponse(items: List<Venue>) {
        venuesAdapter.list = mutableListOf<Venue>().apply {
            addAll(venuesAdapter.list)
            addAll(items)
        }

        offset = binding.recyclerViewVenue.adapter?.itemCount ?: 0

        if (items.size != limit) {
            isEndOfList = true
        }
    }

    private fun setupLocationBroadcast() {
        locationBroadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                checkLocationAndUpdateIfNeeded()
            }
        }

        val intentFilter = IntentFilter(LOCATION_CHECKER_BROADCAST)
        LocalBroadcastManager.getInstance(requireContext())
            .registerReceiver(locationBroadcast, intentFilter)
    }

    private fun checkLocationAndUpdateIfNeeded() {
        getConvertLocation(currentLatLng)?.let { location ->
            viewModel.getCheckLocationChanged(location.latitude, location.longitude)
        }
    }

    private fun getConvertLocation(location: String): Location? {
        return try {
            val latLng = location.split(",")
            return Location("").apply {
                latitude = latLng[0].toDouble()
                longitude = latLng[1].toDouble()
            }
        } catch (ex: Exception) {
            null
        }
    }

    override fun onDestroyView() {
        LocalBroadcastManager.getInstance(requireActivity()).unregisterReceiver(locationBroadcast)
        super.onDestroyView()
    }
}
