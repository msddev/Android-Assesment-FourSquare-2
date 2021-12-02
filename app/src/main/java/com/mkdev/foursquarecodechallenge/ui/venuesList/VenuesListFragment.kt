package com.mkdev.foursquarecodechallenge.ui.venuesList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkdev.domain.model.VenueParams
import com.mkdev.domain.model.VenueUIModel
import com.mkdev.foursquarecodechallenge.base.BaseFragment
import com.mkdev.foursquarecodechallenge.databinding.FragmentVenuesListBinding
import com.mkdev.foursquarecodechallenge.extension.observe
import com.mkdev.presentation.viewmodels.VenuesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VenuesListFragment : BaseFragment<FragmentVenuesListBinding, VenuesListViewModel>() {

    private val limit = 20
    private var offset = 0

    @Inject
    lateinit var venuesAdapter: VenueAdapter

    override val viewModel: VenuesListViewModel by viewModels()

    override fun getViewBinding(): FragmentVenuesListBinding =
        FragmentVenuesListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getVenues(
            VenueParams(
                latLng = "35.730673, 51.458880",
                limit = limit,
                offset = offset
            )
        )
        observe(viewModel.venuesList, ::onViewStateChange)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewVenue.apply {
            adapter = venuesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        venuesAdapter.setItemClickListener { venue ->
            findNavController().navigate(
                VenuesListFragmentDirections.actionVenuesListFragmentToVenueDetailFragment(
                    venueId = venue.id,
                    venueDistance = venue.distance.toString()
                )
            )
        }
    }

    private fun onViewStateChange(event: VenueUIModel) {
        if (event.isRedelivered) return
        when (event) {
            is VenueUIModel.Loading -> {
                handleLoading(true)
            }
            is VenueUIModel.Success -> {
                handleLoading(false)
                venuesAdapter.list = event.data
            }
            is VenueUIModel.Error -> {
                handleErrorMessage(event.error)
            }
        }
    }

}