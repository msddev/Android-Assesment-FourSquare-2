package com.mkdev.foursquarecodechallenge.ui.venuesList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mkdev.domain.model.Venue
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
    private var isEndOfList: Boolean = false

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
}