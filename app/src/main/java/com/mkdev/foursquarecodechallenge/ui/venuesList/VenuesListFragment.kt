package com.mkdev.foursquarecodechallenge.ui.venuesList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkdev.domain.model.VenueUIModel
import com.mkdev.foursquarecodechallenge.base.BaseFragment
import com.mkdev.foursquarecodechallenge.databinding.FragmentVenuesListBinding
import com.mkdev.foursquarecodechallenge.extension.observe
import com.mkdev.presentation.viewmodels.VenuesListViewModel
import javax.inject.Inject

class VenuesListFragment : BaseFragment<FragmentVenuesListBinding, VenuesListViewModel>() {

    @Inject
    lateinit var venuesAdapter: VenueAdapter

    override val viewModel: VenuesListViewModel by viewModels()

    override fun getViewBinding(): FragmentVenuesListBinding =
        FragmentVenuesListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getVenues()
        observe(viewModel.venuesList, ::onViewStateChange)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewVenue.apply {
            adapter = venuesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        venuesAdapter.setItemClickListener { character ->
            findNavController().navigate(
                VenueListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                    character.id.toLong()
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